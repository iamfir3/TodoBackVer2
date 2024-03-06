package com.example.todobackver2.service;

import com.example.todobackver2.dto.CommentDto;
import com.example.todobackver2.request.CommentRequest;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllCommentByTaskId(Long taskId);

    CommentDto create(CommentRequest commentRequest);
}
