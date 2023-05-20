package com.example.todobackver2.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.request.UserRequest;
import com.example.todobackver2.response.UserResponse;
import com.example.todobackver2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("user")
public class userController {
    @Autowired
    UserService userService;

    @Autowired
    Cloudinary cloudinary;

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
    @PostMapping("/update")
    @CrossOrigin
    public UserResponse updateUser(@RequestParam("file")MultipartFile file,@ModelAttribute("userName") String userName,@ModelAttribute("userId") Long userId,@ModelAttribute("email") String email) throws IOException {
        UserResponse userResponse=new UserResponse();
        Map result =cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageUrl= (String) result.get("secure_url");;
        AuthDto authDto=new AuthDto();
        authDto.setUserName(userName);
        authDto.setEmail(email);
        authDto.setAvatar(imageUrl);
        AuthDto storedUser=userService.updateUserByIdWithImage(userId,authDto);
        BeanUtils.copyProperties(storedUser,userResponse);
        return userResponse;
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
