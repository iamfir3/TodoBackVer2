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
    public ResponseTemplate getAllWorkspace(@PathVariable Long userId){
        try{
            List<WorkspaceDto> returnValue=workspaceService.getAllWorkspacesByUserId(userId);
            return ResponseTemplate.responseSuccess(returnValue);
        }
        catch(Exception ex){
            return ResponseTemplate.responseError(ex.getMessage());
        }
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
