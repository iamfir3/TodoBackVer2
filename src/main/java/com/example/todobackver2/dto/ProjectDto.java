package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private String projectName;
    private Date dayBegin;
    private Date dayEnd;
    private String description;
    private String workspaceId;
    private long totalProjects;
    private List<UserDto> users;
    private List<TaskDto> tasks;
}
