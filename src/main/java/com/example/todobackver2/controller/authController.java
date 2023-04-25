package com.example.todobackver2.controller;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.request.AuthRequest;
import com.example.todobackver2.response.AuthResponse;
import com.example.todobackver2.service.AuthService;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("auth")
public class authController {
    @Autowired
    AuthService authService;
    @CrossOrigin
    @PostMapping
    public AuthResponse register(@RequestBody AuthRequest authDetail){
        AuthResponse returnValue=new AuthResponse();
        AuthDto authDto=new AuthDto();
        BeanUtils.copyProperties(authDetail,authDto);
        AuthDto storedUser= authService.createUser(authDto);
        BeanUtils.copyProperties(storedUser,returnValue);
        return returnValue;
    }

    @PostMapping("/reset")
    @CrossOrigin
    public AuthResponse reset(@RequestBody AuthRequest authDetail){
        AuthResponse returnValue=new AuthResponse();
        AuthDto authDto=new AuthDto();
        BeanUtils.copyProperties(authDetail,authDto);
        AuthDto storedUser= authService.resetPassword(authDto);
        BeanUtils.copyProperties(storedUser,returnValue);
        return returnValue;
    }
}
