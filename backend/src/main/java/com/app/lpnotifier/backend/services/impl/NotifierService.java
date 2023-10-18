package com.app.lpnotifier.backend.services.impl;

import com.app.lpnotifier.backend.model.licitation;
import com.app.lpnotifier.backend.services.lpService;
import com.app.lpnotifier.backend.services.notificationService;
import com.app.lpnotifier.backend.services.urlPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;


import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotifierService {
    private final String linkAp= "https://lpnotifier-frontend.vercel.app";
    private final lpService lpService;
    private final urlPreferencesService urlPreferencesService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private notificationService notificationService;

    public NotifierService(com.app.lpnotifier.backend.services.lpService lpService, com.app.lpnotifier.backend.services.urlPreferencesService urlPreferencesService) {
        this.lpService = lpService;
        this.urlPreferencesService = urlPreferencesService;
    }

    @Scheduled(fixedRate = 12 * 60 * 60 * 1000) // 12 horas en milisegundos
    public void sendEmail() {
        ArrayList<String> correos = notificationService.findPreferencesByCorreo();
        for (int i = 0; i < correos.size(); i++) {
            String urlPreferences = urlPreferencesService.findConcatenacionByCorreo(correos.get(i));
            List<String> tiposContrato = Arrays.asList(urlPreferences.split(","));
            List<licitation> recentlt = lpService.findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(tiposContrato);
            int limit = Math.min(10, recentlt.size()); // Limit to a maximum of 10 results
            List<licitation> top10Licitations = recentlt.subList(0, limit);

            // Crear un mensaje MIME
            MimeMessage message = mailSender.createMimeMessage();

            try {
                // Usar MimeMessageHelper para simplificar la creación del mensaje
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

                helper.setFrom("app.lpnotifier@gmail.com");
                helper.setTo(correos.get(i));
                helper.setSubject("Se han encontrado licitaciones de su interés");

                // Construye la tabla HTML con el contenido de recentlt y agrega el botón
                StringBuilder messageText = new StringBuilder();
                messageText.append("<html><body>");
                messageText.append("<p>Estas son las 10 licitaciones más recientes según tus preferencias.</p>");
                messageText.append("<table border='1'>");
                messageText.append("<tr>" +
                                    "<th style=\"background-color: dodgerblue; color: white;\">Nombre</th>" +
                                    "<th style=\"background-color: dodgerblue; color: white;\">Fecha de Publicación</th>" +
                                    "<th style=\"background-color: dodgerblue; color: white;\">Tipo de Contrato</th>" +
                                    "<th style=\"background-color: dodgerblue; color: white;\">Enlace</th>" +
                                   "</tr>");

                for (licitation l : top10Licitations) {
                    messageText.append("<tr>");
                    messageText.append("<td>").append(l.getNombre_procedimiento()).append("</td>");
                    messageText.append("<td>").append(l.getFecha_publicacion()).append("</td>");
                    messageText.append("<td>").append(l.getTipo_de_contrato()).append("</td>");
                    messageText.append("<td><a href=\"").append(l.getUrl()).append("\"" +
                            "style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;\">Enlace</a></td>");
                    messageText.append("</tr>");
                }
                messageText.append("</table>");

                // Agregar el botón de redirección
                messageText.append("<p style=\"text-align: center;\"> Hacer click <a href="+linkAp+" " +
                        "style=\"display: inline-block; padding: 10px 20px; background-color: #157ebb; color: #fff; text-decoration: none; border-radius: 5px;\">" +
                        "LP-Notifier</a> para dirigirse a página home de la aplicación y revisar las licitaciones.</p>");//modificar con la URL de la pagina HOME

                messageText.append("</body></html>");

                helper.setText(messageText.toString(), true); // Indica que el contenido es HTML

                mailSender.send(message);

            // Manejar cualquier excepción que pueda ocurrir al enviar el correo
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("El lote de mails ha sido enviado exitosamente...");
    }
}
