package com.example.todobackver2.repository;

import com.example.todobackver2.dto.ProjectDto;
import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findById(String projectId);

    List<ProjectEntity> findAllByWorkspace(Workspace workspace, Pageable pageable);

    @Query("SELECT COUNT(pj.id) FROM ProjectEntity pj " +
            "WHERE pj.workspace.id=?1")
    int countProject(long workspaceId);
}
