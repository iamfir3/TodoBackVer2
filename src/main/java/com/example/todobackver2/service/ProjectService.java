package com.example.todobackver2.service;

import com.example.todobackver2.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject (ProjectDto projectDto);

    List<ProjectDto> getAllByWorkspaceId(Integer page, Integer limit, String workspaceId);
}
