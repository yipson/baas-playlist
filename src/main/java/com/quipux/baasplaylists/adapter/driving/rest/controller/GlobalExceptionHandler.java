package com.quipux.baasplaylists.adapter.driving.rest.controller;

import com.quipux.baasplaylists.adapter.driving.rest.model.PlaylistExceptionDto;
import com.quipux.baasplaylists.utils.BadRequestException;
import com.quipux.baasplaylists.utils.PlayListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayListNotFoundException.class)
    public ResponseEntity<PlaylistExceptionDto> handlePlaylistNotFoundException(PlayListNotFoundException ex) {
        PlaylistExceptionDto exceptionDto = new PlaylistExceptionDto(ex.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<PlaylistExceptionDto> handleBadRequestException(BadRequestException ex) {
        PlaylistExceptionDto exceptionDto = new PlaylistExceptionDto(ex.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }
}
