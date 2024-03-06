package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.TaskServiceException;
import com.example.todobackver2.dto.CommentDto;
import com.example.todobackver2.dto.TaskDto;
import com.example.todobackver2.dto.UserDto;
import com.example.todobackver2.entity.CommentEntity;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.TaskEntity;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.repository.CommentRepository;
import com.example.todobackver2.repository.ProjectRepository;
import com.example.todobackver2.repository.TaskRepository;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        if(taskDto.getProjectId()==null){
            throw new TaskServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
        }
        ProjectEntity projectEntity=projectRepository.findById(taskDto.getProjectId()).get();
        if(projectEntity==null){
            throw new TaskServiceException("Invalid projectId",-1);
        }
        UserEntity user= userRepository.findById(taskDto.getUserId()).get();
        TaskDto returnValue=new TaskDto();
        TaskEntity taskEntity=new TaskEntity();
        BeanUtils.copyProperties(taskDto,taskEntity);
        taskEntity.setProject(projectEntity);
        taskEntity.setDone(false);
        taskEntity.setUserEntity(user);
        taskEntity.setPriority(taskDto.getPriority());
        TaskEntity storedTask=taskRepository.save(taskEntity);
        BeanUtils.copyProperties(storedTask,returnValue);
        return returnValue;
    }

    @Override
    public List<TaskDto> getAllByProjectId(Integer page, Integer limit, Long projectId) {
        List<TaskDto> returnValue=new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        ProjectEntity projectEntity = projectRepository.findById(projectId).get();
        List<TaskEntity> tasks = taskRepository.findAllByProject(projectEntity);
        for (TaskEntity task : tasks) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(task.getId());
            BeanUtils.copyProperties(task, taskDto);
            taskDto.setTotalTasks(tasks.size());
            returnValue.add(taskDto);
        }
        return returnValue;
    }

    @Override
    public TaskDto checkDone(Long taskId) {
        TaskEntity taskEntity=taskRepository.findById(taskId).get();
        taskEntity.setDone(!taskEntity.isDone());
        TaskEntity saveTask= taskRepository.save(taskEntity);
        TaskDto taskDto=new TaskDto();
        taskDto.setTaskId(taskEntity.getId());
        BeanUtils.copyProperties(saveTask,taskDto);
        return taskDto;
    }

    @Override
    public TaskDto getTaskDetail(Long taskId) {
        TaskDto returnValue=new TaskDto();
        TaskEntity taskEntity=taskRepository.findById(taskId).get();
        ProjectEntity projectEntity =projectRepository.findById(taskEntity.getId()).get();
        BeanUtils.copyProperties(taskEntity,returnValue);
        returnValue.setProjectId(projectEntity.getId());
        returnValue.setProjectName(projectEntity.getProjectName());
        returnValue.setUserId(taskEntity.getUserEntity().getId());
        returnValue.setUserAvatar(taskEntity.getUserEntity().getAvatar());
        UserEntity userEntity =userRepository.findById(taskEntity.getUserEntity().getId()).get();
        List<CommentEntity> commentEntities=commentRepository.findByTask(taskEntity);
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
        UserDto userDto =new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        returnValue.setUserDto(userDto);
        returnValue.setTaskId(taskEntity.getId());
        returnValue.setCommentDtos(commentDtos);
        return returnValue;
    }
}
