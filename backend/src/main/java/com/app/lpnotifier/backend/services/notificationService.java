package com.app.lpnotifier.backend.services;

import com.app.lpnotifier.backend.model.notification;

import java.util.ArrayList;
import java.util.List;

public interface notificationService {
    notification savePreferences(String correo) throws Exception;
    notification findNotificationByCorreo(String correo);
    List<notification> list_all_nt();
    ArrayList<String> findPreferencesByCorreo();
    List<Object[]> findLicitationsByCorreo(String correo);
    List<Boolean> getLicitationsAsBooleans(String correo);
    notification updatePreferences(String correo, List<Boolean> preferences) throws Exception;
    List<Integer[]> countnotificationBynotification();
}
