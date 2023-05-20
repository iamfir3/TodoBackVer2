package com.example.todobackver2.service;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AuthDto getUser(String email);
    AuthDto getUserByAccessToken(String accessToken);

    UserEntity getUserById(Long userId);

    void updateUserById(Long userId, UserEntity userEntity);

    AuthDto updateUserByIdWithImage(Long userId, AuthDto authDto);
}
