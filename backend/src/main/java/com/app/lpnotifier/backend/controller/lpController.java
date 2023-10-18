package com.app.lpnotifier.backend.controller;

import com.app.lpnotifier.backend.model.licitation;
import com.app.lpnotifier.backend.services.lpService;
import com.app.lpnotifier.backend.services.urlPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lp")
@RequiredArgsConstructor
public class lpController {

    private final lpService lpService;
    private final urlPreferencesService urlPreferencesService;

    @GetMapping("/")
    public ResponseEntity<List<licitation>> getAllLicitations(){
        List<licitation> licitations = lpService.list_all_lp();
        if (licitations == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(licitations, HttpStatus.OK);
        }
    }

    @GetMapping("/{correo}")
    public ResponseEntity<List<licitation>>findLicitationByTipoDeContrato(@PathVariable("correo") String correo){
        String urlPreferences = urlPreferencesService.findConcatenacionByCorreo(correo);
        List<String> tiposContrato = Arrays.asList(urlPreferences.split(","));
        List<licitation> lt = lpService.findLicitationByTipoDeContrato(tiposContrato);
        return new ResponseEntity<>(lt, HttpStatus.OK);
    }

    /*@GetMapping("/url/{correo}")
    public ResponseEntity<String>findConcatenacionByCorreo(@PathVariable("correo") String correo){
        String urlPreferences = urlPreferencesService.findConcatenacionByCorreo(correo);
        System.out.println(urlPreferences);
        return new ResponseEntity<>(urlPreferences, HttpStatus.OK);
    }*/

    @GetMapping("/countpreferences")
    public ResponseEntity<List<String>> getCountLicitation(){
        List<String> countL = lpService.countlicitationsByTipoDeContrato();
        return new ResponseEntity<>(countL, HttpStatus.OK);
    }

    @GetMapping("/recently/{correo}")
    public ResponseEntity<List<licitation>>findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(@PathVariable("correo") String correo ){
        String urlPreferences = urlPreferencesService.findConcatenacionByCorreo(correo);
        List<String> tiposContrato = Arrays.asList(urlPreferences.split(","));
        List<licitation> recentlt = lpService.findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(tiposContrato);
        int limit = Math.min(10, recentlt.size()); // Limit to a maximum of 10 results
        List<licitation> top10Licitations = recentlt.subList(0, limit);
        return new ResponseEntity<>(top10Licitations, HttpStatus.OK);
    }

    @GetMapping("/count/{correo}")
    public ResponseEntity<List<String>>countlicitationByTipoDeContrato(@PathVariable("correo") String correo){
        String urlPreferences = urlPreferencesService.findConcatenacionByCorreo(correo);
        List<String> tiposContrato = Arrays.asList(urlPreferences.split(","));
        List<String> countlt = lpService.countlicitationByTipoDeContrato(tiposContrato);
        return new ResponseEntity<>(countlt, HttpStatus.OK);
    }



}
