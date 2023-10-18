package com.app.lpnotifier.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class exceptionNotFound extends RuntimeException {
    private static final long serialVersionID = 1L;

    public exceptionNotFound(String message) {
        super(message);
    }
}
