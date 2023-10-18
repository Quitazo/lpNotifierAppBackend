package com.app.lpnotifier.backend.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("El usuario con ese correo o nombre de usuario no existe en el sistema");
    }
}
