package com.example.todobackver2.service;

import com.example.todobackver2.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllByProjectId(Integer page, Integer limit, String projectId);
}
