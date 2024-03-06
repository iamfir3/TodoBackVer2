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
    private Long id;
    private Date dayBegin;
    private Date dayEnd;
    private String description;
    private Long workspaceId;
    private long totalProjects;
    private List<UserDto> users;
    private List<Long> usersId;
    private List<TaskDto> tasks;
}
