package com.example.todobackver2.Exception;

import lombok.Getter;

@Getter
public class AuthExceptions extends RuntimeException{
    private int status;
    public AuthExceptions(String message,int status){
        super(message);
        this.status=status;
    }

}
