package com.example.todobackver2.repository;

import com.example.todobackver2.dto.CommentDto;
import com.example.todobackver2.entity.CommentEntity;
import com.example.todobackver2.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    List<CommentEntity> findByTask(TaskEntity taskEntity);
}
