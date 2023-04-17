package com.example.todobackver2.controller;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.TaskServiceException;
import com.example.todobackver2.Exception.WorkspaceServiceException;
import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.dto.TaskDto;
import com.example.todobackver2.request.TaskRequest;
import com.example.todobackver2.response.GetAllProjectResponse;
import com.example.todobackver2.response.GetAllTasksResponse;
import com.example.todobackver2.response.ProjectResponse;
import com.example.todobackver2.response.TaskReponse;
import com.example.todobackver2.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class taskController {
//
//    @Autowired
//    TaskService taskService;
//    @PostMapping
//    @CrossOrigin
//    public TaskReponse createTask(@RequestBody TaskRequest taskRequest){
//        TaskReponse returnValue=new TaskReponse();
//        TaskDto taskDto=new TaskDto();
//        BeanUtils.copyProperties(taskRequest,taskDto);
//        TaskDto storedTask=taskService.createTask(taskDto);
//        BeanUtils.copyProperties(storedTask,returnValue);
//        returnValue.setMessage("Task created");
//        returnValue.setStatus(0);
//        return returnValue;
//    }
//
//    @GetMapping("/{projectId}")
//    @CrossOrigin
//    public GetAllTasksResponse<List<TaskReponse>> getAllTasks(@PathVariable String projectId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit) {
//        GetAllTasksResponse returnValue = new GetAllTasksResponse();
//        try {
//            List<TaskDto> tasks = taskService.getAllByProjectId(page, limit, projectId);
//            if (tasks.size() > 0) {
//                returnValue.setTasksCount(tasks.get(0).getTotalTasks());
//            } else returnValue.setTasksCount(0);
//
//            returnValue.setTasks(tasks);
//            returnValue.setMessage("Get succesfully");
//            returnValue.setStatus(0);
//
//        } catch (TaskServiceException e) {
//            throw new TaskServiceException(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage(), ErrorMessage.INTERVAL_SERVER_ERROR.getStatus());
//        }
//        return returnValue;
//    }
}
