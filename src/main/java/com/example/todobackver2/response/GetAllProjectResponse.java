package com.example.todobackver2.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProjectResponse <T>{

    private long projectsCount;
    T projects;
    private String message;
    private int status;
}
