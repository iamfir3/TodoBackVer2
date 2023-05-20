package com.example.todobackver2.service;

import com.example.todobackver2.dto.WorkspaceDto;
import com.example.todobackver2.entity.UserEntity;

import java.util.List;

public interface WorkspaceService {
    WorkspaceDto createWorkspace(String workspaceName,Long userId);

    List<WorkspaceDto> getAllByUserId(Integer page, Integer limit, Long userId);

    List<UserEntity> getAllWorkers(Long workspaceId,Long currentUserId);

    List<WorkspaceDto> getAllWorkspacesByUserId(Long userId);

    WorkspaceDto getWorkspaceById(Long workspaceId);

    void joinWorkspace(Long userId, Long workspaceId);
}
