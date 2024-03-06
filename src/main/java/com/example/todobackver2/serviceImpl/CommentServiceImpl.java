package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.dto.CommentDto;
import com.example.todobackver2.dto.UserDto;
import com.example.todobackver2.entity.CommentEntity;
import com.example.todobackver2.entity.TaskEntity;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.repository.CommentRepository;
import com.example.todobackver2.repository.TaskRepository;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.request.CommentRequest;
import com.example.todobackver2.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<CommentDto> getAllCommentByTaskId(Long taskId) {
        TaskEntity task=taskRepository.findById(taskId).get();
        List<CommentEntity> commentEntities=commentRepository.findByTask(task);
        List<CommentDto> commentDtos=new ArrayList<>();
        for(CommentEntity commentEntity:commentEntities){
            CommentDto commentDto=new CommentDto();
            UserEntity user= commentEntity.getUser();
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(user,userDto);
            BeanUtils.copyProperties(commentEntity,commentDto);
            commentDto.setUser(userDto);
            commentDtos.add(commentDto);

        }
        return commentDtos;
    }

    @Override
    public CommentDto create(CommentRequest commentRequest) {
        UserEntity user=userRepository.findById(commentRequest.getUserId()).get();
        TaskEntity task=taskRepository.findById(commentRequest.getTaskId()).get();
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setUser(user);
        commentEntity.setContent(commentRequest.getContent());
        commentEntity.setTask(task);
        CommentEntity storedComment= commentRepository.save(commentEntity);
        CommentDto returnValue=new CommentDto();
        BeanUtils.copyProperties(storedComment,returnValue);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        returnValue.setUser(userDto);
        return returnValue;
    }
}
