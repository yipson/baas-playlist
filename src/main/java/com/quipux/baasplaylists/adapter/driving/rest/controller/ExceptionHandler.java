package com.quipux.baasplaylists.adapter.driving.rest.controller;

import com.quipux.baasplaylists.adapter.driving.rest.model.PlaylistExceptionDto;
import com.quipux.baasplaylists.utils.BadRequestException;
import com.quipux.baasplaylists.utils.DuplicateRecordException;
import com.quipux.baasplaylists.utils.PlayListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PlayListNotFoundException.class)
    public ResponseEntity<PlaylistExceptionDto> handlePlaylistNotFoundException(PlayListNotFoundException ex) {
        PlaylistExceptionDto exceptionDto = new PlaylistExceptionDto(ex.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<PlaylistExceptionDto> handleBadRequestException(BadRequestException ex) {
        PlaylistExceptionDto exceptionDto = new PlaylistExceptionDto(ex.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<PlaylistExceptionDto> handleDuplicateRecordException(DuplicateRecordException ex) {
        PlaylistExceptionDto exceptionDto = new PlaylistExceptionDto(ex.getMessage(), String.valueOf(HttpStatus.CONFLICT.value()));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exceptionDto);
    }
}
