package com.example.todobackver2.controller;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("forgot")
public class ForgotPasswordController {
    @Autowired
    UserService userService;

    @PostMapping()
    @CrossOrigin
    public boolean checkEmail(@RequestBody AuthDto authDto)
    {
        System.out.println(authDto.getEmail());
        AuthDto user=userService.getUser(authDto.getEmail());
        if(user!=null)
            return true;
        else return false;
    }
}
