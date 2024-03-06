package com.example.todobackver2.repository;

import com.example.todobackver2.entity.ProjectEntity;
import com.example.todobackver2.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity,Long> {
    TaskEntity findById(String taskId);

    List<TaskEntity> findAllByProject(ProjectEntity projectEntity);
}
