package com.example.todobackver2.repository;

import com.example.todobackver2.CompositeKey.Workspace_user_id;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.entity.Workspace_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Workspace_userRepository extends JpaRepository<Workspace_user, Workspace_user_id> {

    @Query(value="SELECT * FROM workspace_user WHERE workspace_id=?1",nativeQuery = true)
    List<Long> findAllByWorkspaceId(Long workspaceId);

    List<Workspace_user> findAllByUserId(Long userId);
}
