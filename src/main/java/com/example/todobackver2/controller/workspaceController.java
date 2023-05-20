package com.example.todobackver2.controller;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.WorkspaceServiceException;
import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.dto.WorkspaceDto;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.entity.Workspace;
import com.example.todobackver2.request.WorkspaceRequest;
import com.example.todobackver2.response.*;
import com.example.todobackver2.service.ProjectService;
import com.example.todobackver2.service.WorkspaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("workspace")
public class workspaceController {

    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    ProjectService projectService;

    @PostMapping
    @CrossOrigin
    public WorkspaceResponse createWorkspace(@RequestBody WorkspaceRequest workspaceRequest) {
        WorkspaceResponse returnValue = new WorkspaceResponse();
        WorkspaceDto workspaceDto = workspaceService.createWorkspace(workspaceRequest.getWorkspaceName(), workspaceRequest.getUserId());
        BeanUtils.copyProperties(workspaceDto, returnValue);
        returnValue.setMessage("Workspace created");
        returnValue.setId(workspaceDto.getWorkspaceId());
        return returnValue;
    }

//    @GetMapping("/{workspaceId}")
//    @CrossOrigin
//    public GetAllProjectResponse<List<ProjectResponse>> getAllProject(@PathVariable String workspaceId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit) {
//        GetAllProjectResponse returnValue = new GetAllProjectResponse();
//        try {
//            List<ProjectDto> projects = projectService.getAllByWorkspaceId(page, limit, workspaceId);
//            if (projects.size() > 0) {
//                returnValue.setProjectsCount(projects.get(0).getTotalProjects());
//            } else returnValue.setProjectsCount(0);
//
//            returnValue.setProjects(projects);
//            returnValue.setMessage("Get succesfully");
//            returnValue.setStatus(0);
//
//        } catch (WorkspaceServiceException e) {
//            throw new WorkspaceServiceException(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage(), ErrorMessage.INTERVAL_SERVER_ERROR.getStatus());
//        }
//        return returnValue;
//    }
//    @GetMapping
//    @CrossOrigin
//    public GetAllWorkspacesResponse<List<WorkspaceResponse>> getAllWorkspace(@RequestParam(value = "userId", defaultValue = "") String userId,@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit){
//        GetAllWorkspacesResponse returnValue=new GetAllWorkspacesResponse();
//        try {
//            List<WorkspaceDto> workspaces = workspaceService.getAllByUserId(page, limit,userId);
//            if (workspaces.size() > 0) {
//                returnValue.setTotalWorkspace(workspaces.get(0).getTotalWorkspaces());
//            } else returnValue.setTotalWorkspace(0);
//
//            returnValue.setWorkspaces(workspaces);
//            returnValue.setMessage("Get succesfully");
//            returnValue.setStatus(0);
//
//        } catch (WorkspaceServiceException e) {
//            throw new WorkspaceServiceException(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage(), ErrorMessage.INTERVAL_SERVER_ERROR.getStatus());
//        }
//        return returnValue;
//    }
    @GetMapping("/{workspaceId}/{currentUserId}")
    @CrossOrigin
    public GetAllUsersResponse<AuthDto> getAllCoWorkers(@PathVariable Long workspaceId, @PathVariable Long currentUserId){

        GetAllUsersResponse returnValue=new GetAllUsersResponse();
        List<AuthDto> authDtos=new ArrayList<>();
        List<UserEntity> users=workspaceService.getAllWorkers(workspaceId,currentUserId);
        for(UserEntity user:users){
            AuthDto authDto=new AuthDto();
            BeanUtils.copyProperties(user,authDto);
            authDto.setUserId(user.getId());
            authDtos.add(authDto);
        }
            returnValue.setUsers(authDtos);
            returnValue.setMessage("success");
            returnValue.setStatus(0);
            returnValue.setTotalUsers(authDtos.size());
                return  returnValue;

    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public List<WorkspaceDto> getAllWorkspace(@PathVariable Long userId){
        List<WorkspaceDto> returnValue=workspaceService.getAllWorkspacesByUserId(userId);
        return returnValue;
    }
    @GetMapping("/getWorkspace/{workspaceId}")
    @CrossOrigin
    public WorkspaceDto getWorkspace(@PathVariable Long workspaceId){
       WorkspaceDto returnValue=workspaceService.getWorkspaceById(workspaceId);
        return returnValue;
    }

    @PostMapping("/joinWorkspace")
    @CrossOrigin
    public String joinWorkspace(@RequestParam("userId") Long userId,@RequestParam("workspaceId") Long workspaceId )
    {
        workspaceService.joinWorkspace(userId,workspaceId);
        return "success";
    }
}
