package com.quipux.baasplaylists.auth.controller;

import com.quipux.baasplaylists.auth.controller.model.AuthenticationRq;
import com.quipux.baasplaylists.auth.controller.model.AuthenticationRs;
import com.quipux.baasplaylists.auth.controller.model.UserDto;
import com.quipux.baasplaylists.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRq authenticationRq) throws Exception {
        final String jwt =  userService.authenticate(authenticationRq);
        return ResponseEntity.ok(new AuthenticationRs(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto user) {

        return ResponseEntity.ok(userService.registerUser(user));
    }
}
