package com.example.todobackver2.repository;

import com.example.todobackver2.CompositeKey.Project_user_id;
import com.example.todobackver2.dto.UserDto;
import com.example.todobackver2.entity.Project_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Project_userRepository extends JpaRepository<Project_user, Project_user_id> {
    List<Project_user> findAllByProjectId(Long id);

    List<Project_user> findAllByProjectIdAndUserId(Long id, Long userId);

    List<Project_user> findAllByUserId(Long userId);

    Project_user findByProjectId(Long id);

    Project_user findFirstByProjectId(Long id);
}
