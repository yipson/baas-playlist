package com.quipux.baasplaylists.auth.service;

import com.quipux.baasplaylists.auth.controller.model.AuthenticationRq;
import com.quipux.baasplaylists.auth.controller.model.UserDto;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    String authenticate(AuthenticationRq authenticationRq);
}
