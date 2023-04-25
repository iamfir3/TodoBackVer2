package com.example.todobackver2.controller;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.request.UserRequest;
import com.example.todobackver2.response.UserResponse;
import com.example.todobackver2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class userController {
    @Autowired
    UserService userService;

    @PostMapping
    @CrossOrigin(origins = "*")
    public UserResponse getUser(@RequestBody UserRequest userRequest) {
        UserResponse returnValue = new UserResponse();
        AuthDto userDto=userService.getUser(userRequest.getEmail());
        BeanUtils.copyProperties(userDto, returnValue);
        returnValue.setMessage("Success");
        returnValue.setStatus(0);
        return returnValue;
    }

    @GetMapping
    @CrossOrigin
    public UserResponse getUserByAccessToken(@RequestHeader("Authorization") String authorization) {
        UserResponse returnValue = new UserResponse();
        AuthDto userDto = userService.getUserByAccessToken(authorization);
        BeanUtils.copyProperties(userDto, returnValue);
        returnValue.setMessage("Success");
        returnValue.setStatus(0);
        return returnValue;
    }



}
