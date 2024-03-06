package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private UserDto user;
    private String content;
    private ProjectDto projectDto;
    private TaskDto taskDto;
    private CommentDto commentDto;
    private String Link;
}
