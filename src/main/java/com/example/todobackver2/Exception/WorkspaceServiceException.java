package com.example.todobackver2.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;
    private int status;

}
