package com.example.todobackver2.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceResponse {
    private String message;
    private int status;
    private Long id;
}
