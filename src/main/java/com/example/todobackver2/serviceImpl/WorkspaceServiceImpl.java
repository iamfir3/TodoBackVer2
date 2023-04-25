package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.WorkspaceServiceException;
import com.example.todobackver2.Utils.GenerateId;
import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.dto.WorkspaceDto;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.entity.Workspace;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.repository.WorkspaceRepository;
import com.example.todobackver2.repository.Workspace_userRepository;
import com.example.todobackver2.request.WorkspaceRequest;
import com.example.todobackver2.service.WorkspaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Workspace_userRepository workspace_userRepository;

    @Override
    public WorkspaceDto createWorkspace(String workspaceName, String userId) {
        UserEntity user = userRepository.findById(userId);
        if (userId == null) {
            throw new WorkspaceServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
        }
        if (user == null) {
            throw new WorkspaceServiceException("Invalid userId", -1);
        }

        Workspace workspaceEntity = new Workspace();
        workspaceEntity.setWorkspaceName(workspaceName);
        workspaceEntity.setUser(user);

        String workspaceId = UUID.randomUUID().toString();
        Workspace workspace = workspaceRepository.findById(workspaceId);
        while (workspace != null) {
            workspaceId = UUID.randomUUID().toString();
            workspace = workspaceRepository.findById(workspaceId);
        }


        Workspace storedWorkspace = workspaceRepository.save(workspaceEntity);

        WorkspaceDto returnValue = new WorkspaceDto();
        BeanUtils.copyProperties(storedWorkspace, returnValue);
        return returnValue;
    }

    @Override
    public List<WorkspaceDto> getAllByUserId(Integer page, Integer limit, String userId) {
        List<WorkspaceDto> returnValue=new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        UserEntity userEntity = userRepository.findById(userId);

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


}
