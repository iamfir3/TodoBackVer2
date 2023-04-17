package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.TaskServiceException;
import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.dto.TaskDto;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.TaskEntity;
import com.example.todobackver2.entity.Workspace;
import com.example.todobackver2.repository.ProjectRepository;
import com.example.todobackver2.repository.TaskRepository;
import com.example.todobackver2.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public TaskDto createTask(TaskDto taskDto) {
        if(taskDto.getProjectId()==null){
            throw new TaskServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
        }
        ProjectEntity projectEntity=projectRepository.findById(taskDto.getProjectId());
        if(projectEntity==null){
            throw new TaskServiceException("Invalid projectId",-1);
        }
        TaskDto returnValue=new TaskDto();
        TaskEntity taskEntity=new TaskEntity();
        BeanUtils.copyProperties(taskDto,taskEntity);
        taskEntity.setProject(projectEntity);
        String taskId= UUID.randomUUID().toString();
        TaskEntity task=taskRepository.findById(taskId);
        while(task!=null){
            taskId= UUID.randomUUID().toString();
            task=taskRepository.findById(taskId);
        }
        TaskEntity storedTask=taskRepository.save(taskEntity);
        BeanUtils.copyProperties(storedTask,returnValue);
        return returnValue;
    }

    @Override
    public List<TaskDto> getAllByProjectId(Integer page, Integer limit, String projectId) {
        List<TaskDto> returnValue=new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        ProjectEntity projectEntity = projectRepository.findById(projectId);
        Page<TaskEntity> tasks = taskRepository.findAllByProject(projectEntity, pageable);
        List<TaskEntity> tasksEntity = tasks.getContent();
        for (TaskEntity task : tasksEntity) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, tasksEntity);
            taskDto.setTotalTasks(tasks.getTotalElements());
            returnValue.add(taskDto);
        }
        return returnValue;
    }
}
