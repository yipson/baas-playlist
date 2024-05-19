package com.quipux.baasplaylists.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String mensaje) {
        super(mensaje);
    }
}
