package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String taskId;
    private String message;
    private int status;
    private String projectId;
    private String taskName;
    private Date dayBegin;
    private Date dayEnd;
    private boolean isDone;
    private long totalTasks;
}
