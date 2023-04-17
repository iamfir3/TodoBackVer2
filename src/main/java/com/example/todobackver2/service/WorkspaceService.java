package com.example.todobackver2.service;

import com.example.todobackver2.dto.WorkspaceDto;
import com.example.todobackver2.request.WorkspaceRequest;

import java.util.List;

public interface WorkspaceService {
    WorkspaceDto createWorkspace(String workspaceName,String userId);

    List<WorkspaceDto> getAllByUserId(Integer page, Integer limit, String userId);
}
