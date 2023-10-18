package com.app.lpnotifier.backend.services.impl;

import com.app.lpnotifier.backend.model.urlPreferences;
import com.app.lpnotifier.backend.repositories.urlPreferencesRepository;
import com.app.lpnotifier.backend.services.urlPreferencesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class urlPreferencesServiceImp implements urlPreferencesService {

    private final urlPreferencesRepository urlPreferencesRepository;

    @Override
    public String findConcatenacionByCorreo(String correo) {
        return urlPreferencesRepository.findConcatenacionByCorreo(correo);
    }

}
