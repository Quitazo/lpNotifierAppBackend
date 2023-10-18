package com.app.lpnotifier.backend.controller;

import com.app.lpnotifier.backend.configurations.JwtUtils;
import com.app.lpnotifier.backend.exceptions.UserNotFoundException;
import com.app.lpnotifier.backend.model.AuthCredentials;
import com.app.lpnotifier.backend.model.TokenCredetial;
import com.app.lpnotifier.backend.model.user;
import com.app.lpnotifier.backend.services.impl.EmailService;
import com.app.lpnotifier.backend.services.impl.UserDetailsServiceImpl;
import com.app.lpnotifier.backend.services.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class authController {
    @Qualifier("authenticationManager")
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserServiceImpl userServiceImpl;
    private final JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> authenticate(@RequestBody AuthCredentials request) throws Exception {
//        System.out.println("Datos de credenciales: " + request);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPw()));
        }catch (DisabledException exception) {
            throw new Exception("El usuario no se encuentra verificado.");
        }
        catch (BadCredentialsException exception) {
            throw new Exception("Las credenciales son invalidas.");
        }
        catch (Exception exception) {
            throw new Exception("Error: "+exception.getMessage());
        }
        final UserDetails user  = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(new TokenCredetial(jwtUtils.generateVerificationToken(user)));
        }
        return ResponseEntity.status(400).body("Ha ocurrido un error al iniciar sesión.");
    }

    @GetMapping("/actual-user")
    public UserDetails getActualUser(Principal principal) throws Exception {
        if (principal != null) {
            return this.userDetailsService.loadUserByUsername(principal.getName());
        }
        else {
            throw new Exception("No se pudo cargar el usuario.");
        }
    }
    @GetMapping("/rl-user")
    public List<String> getAuthorities(@RequestParam("token") String token) throws Exception {
        try {
            System.out.println("TOKEN "+token);
            String userEmail = jwtUtils.extractUsername(token);
            List<String> authorities = this.userDetailsService.loadUserByUsername(userEmail).getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return authorities;
        } catch (Exception e) {
            throw new Exception("No se pudo cargar el rol. "+e.getMessage());
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) throws Exception {
        try {
            String userEmail = jwtUtils.extractUsername(token);
            user usr = userServiceImpl.findByEmail(userEmail);

            if(!usr.getEnable()) {
                usr.setEnable(true);
                usr.setVerify_token("");
                userServiceImpl.updateUser(usr);
                return ResponseEntity. ok("Usuario verificado correctamente.");
            } else {
                throw new Exception("El usuario ya se encuentra verificado.");
            }
        } catch (Exception e) {
            throw new Exception("ERROR: "+e.getMessage());
        }
    }

    protected String extractTokenFromBody(String rawData) {
        // Analizar el cuerpo JSON utilizando la biblioteca Gson
        JsonObject jsonObject = new Gson().fromJson(rawData, JsonObject.class);

        // Extraer el token del objeto JSON
        if (jsonObject != null && jsonObject.has("token")) {
            return jsonObject.get("token").getAsString();
        } else {
            throw new IllegalArgumentException("El cuerpo de la solicitud no contiene un token válido");
        }
    }
}
