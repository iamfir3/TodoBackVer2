package com.example.todobackver2.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllWorkspacesResponse <T>{
    T workspaces;
    private String message;
    private int status;
    private long totalWorkspace;
}
