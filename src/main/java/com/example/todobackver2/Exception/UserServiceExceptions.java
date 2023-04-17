package com.example.todobackver2.Exception;

import lombok.Getter;

@Getter
public class UserServiceExceptions extends RuntimeException{
    private int status;
    public UserServiceExceptions(String message,int status){
        super(message);
        this.status=status;
    }
}
