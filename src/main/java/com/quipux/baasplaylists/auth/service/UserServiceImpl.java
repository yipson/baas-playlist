package com.quipux.baasplaylists.auth.service;

import com.quipux.baasplaylists.auth.JwtUtil;
import com.quipux.baasplaylists.auth.controller.model.AuthenticationRq;
import com.quipux.baasplaylists.auth.controller.model.UserDto;
import com.quipux.baasplaylists.auth.entity.UserEntity;
import com.quipux.baasplaylists.auth.repository.UserJpaRepository;
import com.quipux.baasplaylists.utils.exception.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtUtil jwtUtil) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        this.validateUserNameAlreadyExist(userDto.getUsername());
        UserEntity userEntity = UserEntity.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        UserEntity savedUser = this.save(userEntity);
        return UserDto.builder()
                .username(savedUser.getUsername())
                .password(savedUser.getPassword())
                .build();
    }

    private UserEntity save(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(user);
    }

    private void validateUserNameAlreadyExist(String username) {
         if(userJpaRepository.countByUsername(username) > 0) {
             throw new BadRequestException("Username is already taken");
         }
    }

    @Override
    public String authenticate(AuthenticationRq authenticationRq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRq.getUsername(),
                            authenticationRq.getPassword()));
        } catch (Exception e) {
            throw new BadRequestException("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRq.getUsername());
        return jwtUtil.generateToken(userDetails);
    }



}
