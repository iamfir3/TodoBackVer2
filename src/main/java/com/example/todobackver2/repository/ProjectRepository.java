package com.example.todobackver2.repository;

import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity,Long> {
    ProjectEntity findById(String projectId);

    Page<ProjectEntity> findAllByWorkspaceId(Workspace workspace, Pageable pageable);
}
