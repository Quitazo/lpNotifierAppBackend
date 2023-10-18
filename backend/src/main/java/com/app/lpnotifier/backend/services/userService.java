package com.app.lpnotifier.backend.services;

import com.app.lpnotifier.backend.model.user;

import java.util.List;

public interface userService {

    user saveUser(user usr) throws Exception;
    List<user> list_all_users();
    user findByUsername(String username);
    user findByEmail(String email);
    user findUserbyId(Integer id);
    user updateUser(user usr) throws Exception;
    user updatePw(String userEmail, String[] pwsuser) throws Exception;
    void deleteUserById(Integer id);
    List<String> countuserByEnable();
}
