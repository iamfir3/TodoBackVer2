package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.Workspace;
import com.example.todobackver2.repository.ProjectRepository;
import com.example.todobackver2.repository.WorkspaceRepository;
import com.example.todobackver2.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
////        Workspace workspace = workspaceRepository.findByWorkspaceId(projectDto.getWorkspaceId());
////
////        if (workspace == null)
////            throw new ProjectServiceException(ErrorMessage.MISSING_REQUIED_FIELD.getErrorMessage(), ErrorMessage.MISSING_REQUIED_FIELD.getStatus());
////        ProjectDto returnValue = new ProjectDto();
////        ProjectEntity projectEntity = new ProjectEntity();
////
////        BeanUtils.copyProperties(projectDto, projectEntity);
////        String projectId = UUID.randomUUID().toString();
////        ProjectEntity project = projectRepository.findByProjectId(projectId);
////        while (project != null) {
////            projectId = UUID.randomUUID().toString();
////            project = projectRepository.findByProjectId(projectId);
////        }
////        projectEntity.setWorkspaceId(workspace);
////        projectEntity.setProjectId(projectId);
////
////        ProjectEntity storedProject = projectRepository.save(projectEntity);
////        System.out.println(storedProject.getProjectName());
////        BeanUtils.copyProperties(storedProject, returnValue);
//        return returnValue;
        return null;
    }

    @Override
    public List<ProjectDto> getAllByWorkspaceId(Integer page, Integer limit, String workspaceId) {
        return null;
    }

    @Override
    public Page<ProjectDto> getAllProjectsByWorkspace(Pageable pageable, Long workspaceId) {
        Workspace workspace=workspaceRepository.findById(workspaceId).get();
        List<ProjectEntity> projectEntities=projectRepository.findAllByWorkspace(workspace,pageable);
        List<ProjectDto> projectDtos=new ArrayList<>();
        for(ProjectEntity project:projectEntities){
            ProjectDto projectDto=new ProjectDto();
            BeanUtils.copyProperties(project,projectDto);
            projectDtos.add(projectDto);
        }
        long total=projectRepository.countProject(workspace.getId());
        return new PageImpl<>(projectDtos,pageable,total);
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
