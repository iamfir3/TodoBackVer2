package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.ProjectServiceException;
import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.dto.TaskDto;
import com.example.todobackver2.dto.UserDto;
import com.example.todobackver2.entity.*;
import com.example.todobackver2.repository.*;
import com.example.todobackver2.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    Project_userRepository project_userRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;
    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Workspace workspace = workspaceRepository.findById(projectDto.getWorkspaceId()).get();

        if (workspace == null)
            throw new ProjectServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
        ProjectDto returnValue = new ProjectDto();
        ProjectEntity projectEntity = new ProjectEntity();

        BeanUtils.copyProperties(projectDto, projectEntity);
        projectEntity.setWorkspace(workspace);

        ProjectEntity storedProject = projectRepository.save(projectEntity);
        List<Project_user> projectUsers=new ArrayList<>();
        for(Long userId:projectDto.getUsersId()){
            Project_user project_user=new Project_user();
            project_user.setProjectId(storedProject.getId());
            project_user.setUserId(userId);
            projectUsers.add(project_user);
        }
        project_userRepository.saveAll(projectUsers);
        projectEntity.setProjectUsers(projectUsers);
        ProjectEntity storedProject2 = projectRepository.save(projectEntity);
        BeanUtils.copyProperties(storedProject2, returnValue);
            return returnValue;
    }

    @Override
    public List<ProjectDto> getAllByWorkspaceId(Integer page, Integer limit, String workspaceId) {
        return null;
    }

    @Override
    public Page<ProjectDto> getAllProjectsByWorkspace(Pageable pageable, Long workspaceId,Long userId) {
        Workspace workspace=workspaceRepository.findById(workspaceId).get();
//        Page<ProjectEntity> projectEntities2=projectRepository.findAllByWorkspace(workspace,pageable);
        List<ProjectEntity> projectEntities=projectRepository.findAllByWorkspace(workspace.getId(), userId);
        long total=projectRepository.countProject(workspace.getId());
        if(projectEntities!=null){
            List<ProjectDto> projectDtos=new ArrayList<>();
            for(ProjectEntity project:projectEntities){
                ProjectDto projectDto=new ProjectDto();
                BeanUtils.copyProperties(project,projectDto);
                List<TaskEntity> taskEntities=taskRepository.findAllByProject(project);
                List<TaskDto> taskDtos=new ArrayList<>();
                for(TaskEntity taskEntity:taskEntities){
                    TaskDto taskDto=new TaskDto();
                    BeanUtils.copyProperties(taskEntity,taskDto);
                    taskDtos.add(taskDto);
                }
                List<Project_user> project_users=project_userRepository.findAllByProjectId(project.getId());
                List<UserDto> userDtos=new ArrayList<>();
                for(Project_user project_user:project_users){
                    UserEntity user=userRepository.findById(project_user.getUserId()).get();
                    UserDto userDto=new UserDto();
                    BeanUtils.copyProperties(user,userDto);
                    userDtos.add(userDto);
                }
                projectDto.setTasks(taskDtos);
                projectDto.setUsers(userDtos);
                projectDtos.add(projectDto);
            }
            return new PageImpl<>(projectDtos,pageable,total);
        }
        else return new PageImpl<>(null,pageable,total);
    }

    @Override
    public List<UserDto> getAllCoWorkerProject(Long projectId) {
        List<UserDto> userDtos=new ArrayList<>();
        List<Project_user> project_users=project_userRepository.findAllByProjectId(projectId);
        for(Project_user project_user:project_users){
            UserEntity userEntity=userRepository.findById(project_user.getUserId()).get();
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            userDto.setUserId(userEntity.getId());
            userDtos.add(userDto);
        }

        return userDtos;
    }

//    @Override
//    public List<ProjectDto> getAllByWorkspaceId(Integer page, Integer limit, String workspaceId) {
//        List<ProjectDto> returnValue = new ArrayList<>();
//        Pageable pageable = PageRequest.of(page, limit);
//        Workspace workspace = workspaceRepository.findById(workspaceId);
//        Page<ProjectEntity> projects = projectRepository.findAllByWorkspaceId(workspace, pageable);
//        List<ProjectEntity> projectsEntity = projects.getContent();
//        for (ProjectEntity project : projectsEntity) {
//            ProjectDto projectDto = new ProjectDto();
//            BeanUtils.copyProperties(project, projectDto);
//            projectDto.setTotalProjects(projects.getTotalElements());
//            returnValue.add(projectDto);
//        }
//
//        return returnValue;
//    }
}
