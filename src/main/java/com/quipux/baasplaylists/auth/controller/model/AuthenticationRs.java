package com.quipux.baasplaylists.auth.controller.model;

public class AuthenticationRs {
    private final String jwt;

    public AuthenticationRs(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
