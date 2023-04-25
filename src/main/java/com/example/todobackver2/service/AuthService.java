package com.example.todobackver2.service;

import com.example.todobackver2.dto.AuthDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    AuthDto createUser(AuthDto authDto);

    AuthDto resetPassword(AuthDto authDto);
}
