package com.example.todobackver2.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskServiceException extends RuntimeException{
    private int status;
    public TaskServiceException(String message,int status){
        super(message);
        this.status=status;
    }
}
