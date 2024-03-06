package com.example.todobackver2.controller;

import com.example.todobackver2.dto.CommentDto;
import com.example.todobackver2.request.CommentRequest;
import com.example.todobackver2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class commentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{taskId}")
    @CrossOrigin
    public List<CommentDto> getAllCommentByTaskId(@PathVariable Long taskId){
        return commentService.getAllCommentByTaskId(taskId);
    }

    @PostMapping
    @CrossOrigin
    public CommentDto createComment(@RequestBody CommentRequest commentRequest){
        return commentService.create(commentRequest);
    }
}
