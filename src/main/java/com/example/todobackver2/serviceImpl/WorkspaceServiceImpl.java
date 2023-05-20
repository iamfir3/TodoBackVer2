package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.WorkspaceServiceException;
import com.example.todobackver2.dto.WorkspaceDto;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.entity.Workspace;
import com.example.todobackver2.entity.Workspace_user;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.repository.WorkspaceRepository;
import com.example.todobackver2.repository.Workspace_userRepository;
import com.example.todobackver2.service.WorkspaceService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Workspace_userRepository workspace_userRepository;

    @Override
    public WorkspaceDto createWorkspace(String workspaceName, Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if (userId == null) {
            throw new WorkspaceServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
        }
        if (user == null) {
            throw new WorkspaceServiceException("Invalid userId", -1);
        }

        Workspace workspaceEntity = new Workspace();
        workspaceEntity.setWorkspaceName(workspaceName);
        workspaceEntity.setUser(user);

        Workspace storedWorkspace = workspaceRepository.save(workspaceEntity);
        Workspace_user workspace_user=new Workspace_user();
        workspace_user.setWorkspaceId(storedWorkspace.getId());
        workspace_user.setUserId(userId);
        workspace_user.setWorkspace(storedWorkspace);
        workspace_user.setUser(user);
        workspace_userRepository.save(workspace_user);
        WorkspaceDto returnValue = new WorkspaceDto();
        BeanUtils.copyProperties(storedWorkspace, returnValue);
        return returnValue;
    }

    @Override
    public List<WorkspaceDto> getAllByUserId(Integer page, Integer limit, Long userId) {
        List<WorkspaceDto> returnValue=new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        UserEntity userEntity = userRepository.findById(userId).get();

        Page<Workspace> workspaces = workspaceRepository.findAllByUser(userEntity, pageable);
        List<Workspace> workspaceList = workspaces.getContent();
        for (Workspace workspace : workspaceList) {
            WorkspaceDto workspaceDto = new WorkspaceDto();
            BeanUtils.copyProperties(workspace, workspaceDto);
            workspaceDto.setTotalWorkspaces(workspaces.getTotalElements());
            returnValue.add(workspaceDto);
        }
        return returnValue;
    }

    @Override
    public List<UserEntity> getAllWorkers(Long workspaceId,Long currentUserId) {
        List<UserEntity> returnValue=new ArrayList<>();
        List<Long> userIds=workspace_userRepository.findAllByWorkspaceId(workspaceId);
        for(Long id:userIds){
            if(id!=currentUserId) {
                UserEntity user = userRepository.findById(id).get();
                returnValue.add(user);
            }
        }
        return returnValue;
    }

    @Override
    public List<WorkspaceDto> getAllWorkspacesByUserId(Long userId) {
        List<Workspace_user> workspaceUsers=workspace_userRepository.findAllByUserId(userId);
        List<WorkspaceDto> returnValue=new ArrayList<>();
        for(Workspace_user workspace_user:workspaceUsers){
            Workspace workspace=workspaceRepository.findById(workspace_user.getWorkspaceId()).get();
            WorkspaceDto workspaceDto =new WorkspaceDto();
            BeanUtils.copyProperties(workspace,workspaceDto);
            workspaceDto.setWorkspaceId(workspace_user.getWorkspaceId());
            returnValue.add(workspaceDto);

        }
        return returnValue;
    }

    @Override
    public WorkspaceDto getWorkspaceById(Long workspaceId) {
        WorkspaceDto returnValue=new WorkspaceDto();
        Workspace workspace=new Workspace();
        workspace=workspaceRepository.findById(workspaceId).get();
        returnValue.setWorkspaceId(workspace.getId());
        BeanUtils.copyProperties(workspace,returnValue);
        return returnValue;
    }

    @Override
    public void joinWorkspace(Long userId, Long workspaceId) {
        Workspace_user workspace_user=new Workspace_user();
        UserEntity user= userRepository.findById(userId).get();
        Workspace workspace =workspaceRepository.findById(workspaceId).get();
        workspace_user.setUser(user);
        workspace_user.setWorkspace(workspace);
        workspace_user.setUserId(userId);
        workspace_user.setWorkspaceId(workspaceId);
        workspace_userRepository.save(workspace_user);
    }


}
