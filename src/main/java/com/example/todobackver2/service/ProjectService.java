package com.example.todobackver2.service;

import com.example.todobackver2.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject (ProjectDto projectDto);

    List<ProjectDto> getAllByWorkspaceId(Integer page, Integer limit, String workspaceId);

    Page<ProjectDto> getAllProjectsByWorkspace(Pageable pageable,Long workspaceId);
}
