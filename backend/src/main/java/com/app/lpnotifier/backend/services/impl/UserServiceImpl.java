package com.app.lpnotifier.backend.services.impl;

import com.app.lpnotifier.backend.exceptions.UserFoundException;
import com.app.lpnotifier.backend.exceptions.exceptionNotFound;
import com.app.lpnotifier.backend.model.user;
import com.app.lpnotifier.backend.repositories.roleRepository;
import com.app.lpnotifier.backend.repositories.userRepository;
import com.app.lpnotifier.backend.services.userService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements userService {

    private final userRepository userRepository;
    private final roleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public user saveUser(user usr) throws Exception {
        user usrLocal = userRepository.findByUsername(usr.getUsername());
        user usrLocal2 = userRepository.findByEmail(usr.getEmail());
        if (usrLocal != null && usrLocal2 != null) {
            System.out.println("El usuario ya existe" + usr.getEmail()+usr.getUsername());
            throw new UserFoundException("El usuario ya esta presente");
        }
        else {
            usr.setPw(passwordEncoder.encode(usr.getPw()));
            usr.setRole(roleRepository.findRolById(2L));
        }
        return userRepository.save(usr);
    }

    @Override
    public user findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public user findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public List<user> list_all_users() {
        return userRepository.findAll();
    }
    @Override
    public user updateUser(user usr) throws Exception{
        user usrActual = userRepository.findByEmail(usr.getEmail());
        if (usrActual == null) {
            throw new Exception("El usuario no existe");
        } else{
            System.out.println("Usr "+usr.getEmail());
            System.out.println("ACTUAL "+usrActual.getEmail());

            System.out.println("Usr "+usr.getName());
            System.out.println("ACTUAL "+usrActual.getName());

            System.out.println("Usr "+usr.getUsername());
            System.out.println("ACTUAL "+usrActual.getUsername());

            usrActual.setName(usr.getName());
            usrActual.setUsername(usr.getUsername());
            usrActual.setPhone(usr.getPhone());
            return userRepository.save(usrActual);
        }
    }

    @Override
    public user updatePw(String userEmail, String[] pwsuser) throws Exception{
        user usrActual = userRepository.findByEmail(userEmail);
        if (usrActual == null) {
            throw new Exception("El usuario no existe");
        } else{
            if(usrActual.getPw().equals(pwsuser[0])){
                System.out.println("Contraseñas "+usrActual.getPw()+" "+pwsuser[0]);
                System.out.println("Contraseña nueva "+pwsuser[1]);
//                usrActual.setPw(passwordEncoder.encode(pwsuser[1]));
//                return userRepository.save(usr);
                throw new Exception("Las contraseñas no coinciden!!!");
            }
            else {
                throw new Exception("Las contraseñas no coinciden!!!");
            }
        }
    }

    @Override
    public user findUserbyId(Integer id){
        return userRepository.findUserById(id).orElseThrow(() -> new exceptionNotFound("User by id "+ id +" was not found"));
    }
    @Override
    public void deleteUserById(Integer id){
        userRepository.deleteUserById(id);
    }

    @Override
    public List<String> countuserByEnable(){
        return userRepository.countuserByEnable();
    }
}
