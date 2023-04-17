package com.example.todobackver2.controller;

import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.response.GetAllProjectResponse;
import com.example.todobackver2.response.GetAllTasksResponse;
import com.example.todobackver2.response.ProjectResponse;
import com.example.todobackver2.response.TaskReponse;
import com.example.todobackver2.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class projectController {
//
//    @Autowired
//    ProjectService projectService;
//
//    @PostMapping
//    @CrossOrigin
//    private ProjectResponse createProject(@RequestBody ProjectDto projectRequest){
//        ProjectResponse returnValue=new ProjectResponse();
//        ProjectDto projectDto =new ProjectDto();
//        BeanUtils.copyProperties(projectRequest,projectDto);
//        ProjectDto storedValue= projectService.createProject(projectDto);
//        BeanUtils.copyProperties(storedValue,returnValue);
//        returnValue.setMessage("Project created");
//        return returnValue;
//    }
//
//    @GetMapping("")
//    @CrossOrigin
//    private GetAllTasksResponse<List<TaskReponse>> getAllProject(){
//        GetAllTasksResponse returnValue=new GetAllTasksResponse();
//
//        return  returnValue;
//    }
}
