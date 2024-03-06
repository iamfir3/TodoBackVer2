package com.example.todobackver2.repository;

import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findById(String projectId);

    @Query("SELECT new ProjectEntity(p.id,p.projectName,p.dayBegin,p.DayEnd,p.Description,p.createdAt,p.updatedAt)" +
            "FROM ProjectEntity p,Project_user pu " +
            "WHERE p.workspace.id=:workspaceId " +
            "AND pu.projectId=p.id " +
            "AND pu.userId=:userId " +
            "")
    List<ProjectEntity> findAllByWorkspace(@Param(value = "workspaceId") Long workspace,@Param(value = "userId") Long userId);

    @Query("SELECT COUNT(pj.id) FROM ProjectEntity pj " +
            "WHERE pj.workspace.id=?1")
    int countProject(long workspaceId);
}
