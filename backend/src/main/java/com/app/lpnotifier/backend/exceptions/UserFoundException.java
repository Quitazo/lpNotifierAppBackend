package com.app.lpnotifier.backend.exceptions;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("El usuario con ese correo o nombre de usuario existe en el sistema");
    }
    public UserFoundException(String mensaje) {
        super(mensaje);
    }
}
