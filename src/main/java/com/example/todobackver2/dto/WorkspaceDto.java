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
    private Long userId;
    private Long workspaceId;
    private long totalWorkspaces;
}
