package com.example.todobackver2.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceRequest {
    private String workspaceName;
    private Long userId;
}
