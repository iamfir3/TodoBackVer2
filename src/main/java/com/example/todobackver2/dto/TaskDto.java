package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long taskId;
    private String message;
    private int status;
    private Long userId;
    private Long projectId;
    private String projectName;
    private String taskName;
    private Date dayBegin;
    private Date dayEnd;
    private String userAvatar;
    private UserDto userDto;
    private String description;
    private boolean isDone;
    private long totalTasks;
    private int priority;
    private List<CommentDto> commentDtos;
}
