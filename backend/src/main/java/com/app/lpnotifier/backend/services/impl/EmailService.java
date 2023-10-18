package com.app.lpnotifier.backend.services.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    private final String linkAp= "https://lpnotifier-frontend.vercel.app/verify?token=";
    private final SendGrid sendGrid;
    private final String senderEmail;

    public EmailService(@Value("${sendgrid.api.key}") String sendGridApiKey, @Value("${email.sender}") String senderEmail) {
        this.sendGrid = new SendGrid(sendGridApiKey);
        this.senderEmail = senderEmail;
    }


    public void sendVerificationEmail(String recipientEmail, String verificationToken) throws Exception {
        System.out.println("TOKEN DE VERIFICACION " + verificationToken);
        Email from = new Email(senderEmail);
        Email to = new Email(recipientEmail);
        String subject = "Verificación de correo electrónico";
//        String message = "Por favor, haga clic en el siguiente enlace para verificar su correo electrónico: "
//                + "<a href=\"http://localhost:4200/verify?token="+verificationToken+"\">Verificar correo electrónico</a>";

        String message = "<p style=\"color: #666;\">Por favor, haga clic en el siguiente enlace para verificar su correo electrónico:</p>\n" +
                "        <p style=\"text-align: center;\">\n" +
                "            <a href=\""+linkAp+verificationToken+"\"" +
                "               style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;\">\n" +
                "               Verificar correo electrónico\n" +
                "            </a>\n" +
                "        </p>\n" +
                "        <p style=\"color: #666;\">Si no puede hacer clic en el enlace, copie y pegue la siguiente URL en su navegador:</p>\n" +
                "        <p style=\"color: #666;\">"+linkAp+verificationToken+"\"</p>\n" +
                "        <p style=\"color: #666;\">Si no solicitó esta verificación, puede ignorar este correo.</p>\n" +
                "        <p style=\"color: #666;\">Gracias,</p>\n" +
                "        <p style=\"color: #666;\">Su equipo de soporte";

        Content content = new Content("text/html", message);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
//                System.out.println("Correo electrónico enviado con éxito a: " + recipientEmail);
            } else {
                throw new Exception("Error al enviar el correo electrónico: " + response.getBody());
            }
        } catch (IOException ex) {
            throw new Exception("Error al enviar el correo electrónico: " + ex.getMessage());
        }
    }
}