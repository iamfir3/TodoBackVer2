package com.example.todobackver2.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String content;
    private Long userId;
    private Long taskId;
}
