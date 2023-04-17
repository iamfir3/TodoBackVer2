package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDto {
    private String workspaceName;
    private String userId;
    private String workspaceId;
    private long totalWorkspaces;
}
