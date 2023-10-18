package com.app.lpnotifier.backend.services.impl;

import com.app.lpnotifier.backend.model.notification;
import com.app.lpnotifier.backend.repositories.notificationRepository;
import com.app.lpnotifier.backend.services.notificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class notificationServiceImp implements notificationService {

    private notificationRepository notificationRepository;

    @Override
    public notification savePreferences(String email) throws Exception {
        notification ntfLocal = findNotificationByCorreo(email);
        if (ntfLocal == null){
            notification ntf = new notification(
                    email,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false
            );
            return notificationRepository.save(ntf);

        } else {
//            System.out.println("El usuario: "+email+" ya cuenta con preferencias.");
            return null;
        }
    }

    @Override
    public notification findNotificationByCorreo(String correo) {
        return notificationRepository.findNotificationByCorreo(correo);
    }

    @Override
    public ArrayList<String> findPreferencesByCorreo(){
        return notificationRepository.findPreferencesByCorreo();
    }

    @Override
    public List<Object[]> findLicitationsByCorreo(String correo){return notificationRepository.findLicitationsByCorreo(correo);}

    @Override
    public List<Boolean> getLicitationsAsBooleans(String correo) {
        List<Object[]> results = notificationRepository.findLicitationsByCorreo(correo);
        List<Boolean> booleanValues = new ArrayList<>();

        for (Object[] row : results) {
            for (Object value : row) {
                if (value instanceof Boolean) {
                    booleanValues.add((Boolean) value);
                } else if (value instanceof String) {
                    booleanValues.add(Boolean.parseBoolean((String) value));
                }
            }
        }

        return booleanValues;
    }


    @Override
    public List<notification> list_all_nt() {
        return notificationRepository.findAll();
    }

    @Override
    public notification updatePreferences(String email, List<Boolean> preferences) throws Exception {
        notification ntfLocal = findNotificationByCorreo(email);

        if (ntfLocal == null) {
            throw new Exception("No se encontró ninguna preferencia para el correo: " + email);
        }

        if (preferences.size() != 8) {
            throw new IllegalArgumentException("La lista de preferencias debe contener 8 valores booleanos.");
        }
//        System.out.println("PREFERENCES LIST "+preferences.toString());

        ntfLocal.setConcesion(preferences.get(0));
        ntfLocal.setCompraventa(preferences.get(1));
        ntfLocal.setSeguros(preferences.get(2));
        ntfLocal.setND(preferences.get(3));
        ntfLocal.setSuministros(preferences.get(4));
        ntfLocal.setServicios_de_aprovisionamiento(preferences.get(5));
        ntfLocal.setObra(preferences.get(6));
        ntfLocal.setOtros_servicios(preferences.get(7));

        return notificationRepository.save(ntfLocal);
    }

    @Override
    public List<Integer[]> countnotificationBynotification(){
        return notificationRepository.countnotificationBynotification();
    }


}
