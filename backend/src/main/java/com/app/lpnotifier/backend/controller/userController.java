package com.app.lpnotifier.backend.controller;


import com.app.lpnotifier.backend.configurations.JwtUtils;
import com.app.lpnotifier.backend.model.notification;
import com.app.lpnotifier.backend.model.user;
import com.app.lpnotifier.backend.services.impl.EmailService;
import com.app.lpnotifier.backend.services.notificationService;
import com.app.lpnotifier.backend.services.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {
    private final userService userService;
    private final EmailService emailService;
    private final JwtUtils jwtUtils;
    private final notificationService notificationService;

    public userController(userService userService, EmailService emailService, JwtUtils jwtUtils, notificationService notificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
        this.notificationService = notificationService;
    }

    @GetMapping("/usr")
    public ResponseEntity<List<user>> getAllUsers() {
        List<user> users = userService.list_all_users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/usr/{email}")
    public ResponseEntity<user> getUserByEmail(@PathVariable("email") String email) {
        user usr = userService.findByEmail(email);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }
    @PostMapping("/usr")
    public ResponseEntity<user> addUser(@RequestBody user usr) throws Exception {
        UserDetails provisionalUserDetails = new org.springframework.security.core.userdetails.User(
                usr.getEmail(),
                usr.getPw(),
                false,
                true,
                true,
                true,
                Collections.emptyList()
        );
        String verificationToken = jwtUtils.generateVerificationToken(provisionalUserDetails);
        usr.setVerify_token(verificationToken);
        user newUsr = userService.saveUser(usr);
        if(newUsr != null) {
            emailService.sendVerificationEmail(newUsr.getEmail(), verificationToken);
            notificationService.savePreferences(newUsr.getEmail());
            System.out.println(newUsr.getEmail() + " registrado. Se ha enviado un correo electrónico de verificación.");
        } else {
            throw new  Exception("Error al enviar el correo electrónico de verificación.");
        }
        return new ResponseEntity<>(newUsr, HttpStatus.CREATED);
    }
    @PutMapping("/usr")
    public ResponseEntity<user> updateUser(@RequestParam("token") String token, @RequestBody user usr) throws Exception {
        String userEmail = jwtUtils.extractUsername(token);
        usr.setEmail(userEmail);
        user updatedUsr = userService.updateUser(usr);
        return new ResponseEntity<>(updatedUsr, HttpStatus.OK);
    }
    @PutMapping("/usr/updatePw")
    public ResponseEntity<user> updateData(@RequestParam("token") String token, @RequestBody String[] pws) throws Exception {
        String userEmail = jwtUtils.extractUsername(token);
        user updatedUsr = userService.updatePw(userEmail, pws);
        return new ResponseEntity<>(updatedUsr, HttpStatus.OK);
    }
    @DeleteMapping("/usr/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usr/preferences")
    public ResponseEntity<List<notification>>getAllNotification(){
        List<notification> ntfs = notificationService.list_all_nt();
        return new ResponseEntity<>(ntfs, HttpStatus.OK);
    }

    @GetMapping("/usr/pref/{correo}")
    public ResponseEntity<notification>getUserNotification(@PathVariable("correo") String correo){
        notification ntf = notificationService.findNotificationByCorreo(correo);
        return new ResponseEntity<>(ntf, HttpStatus.OK);
    }

    @PutMapping("/usr/preferences/save")
    public ResponseEntity<notification>savePrecerences(@PathVariable("correo") String correo) throws Exception{
        notification saveNotifications = notificationService.savePreferences(correo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usr/correos")
    public ResponseEntity<List<String>>getCorreos(){
        List<String> correos = notificationService.findPreferencesByCorreo();
        return new ResponseEntity<>(correos, HttpStatus.OK);
    }

//    @GetMapping("/usr/preferences/{correo}")
//    public ResponseEntity<List<Object[]>>getUserLicitations(@PathVariable("correo") String correo){
//        List<Object[]> ntfs = notificationService.findLicitationsByCorreo(correo);
//        return new ResponseEntity<>(ntfs, HttpStatus.OK);
//    }

    @GetMapping("/usr/preferences/{correo}")
    public ResponseEntity<List<Boolean>> getUserLicitations(@PathVariable("correo") String correo){
        List<Boolean> preferences = notificationService.getLicitationsAsBooleans(correo);
        return new ResponseEntity<>(preferences, HttpStatus.OK);
    }

    @PutMapping("/usr/savepreference/{correo}")
    public ResponseEntity<notification> updatePreferences(@PathVariable("correo") String correo, @RequestBody List<Boolean> preferences) throws Exception{
        notification update = notificationService.updatePreferences(correo, preferences);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/usr/enable")
    public ResponseEntity<List<String>> countuserByEnable(){
        List<String> enableList = userService.countuserByEnable();
        return new ResponseEntity<>(enableList, HttpStatus.OK);
    }

    @GetMapping("/usr/countPreferences")
    public ResponseEntity<List<Integer>> countPreferences(){
        List<Integer> listCountLicitation = new ArrayList<>();
        List<Integer[]> results = notificationService.countnotificationBynotification();
        for(Integer[] data : results){
            listCountLicitation.add(data[0]);
            listCountLicitation.add(data[1]);
            listCountLicitation.add(data[2]);
            listCountLicitation.add(data[3]);
            listCountLicitation.add(data[4]);
            listCountLicitation.add(data[5]);
            listCountLicitation.add(data[6]);
            listCountLicitation.add(data[7]);
        }
        return new ResponseEntity<>(listCountLicitation, HttpStatus.OK);
    }
}
