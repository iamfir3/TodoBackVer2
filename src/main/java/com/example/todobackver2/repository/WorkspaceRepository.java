package com.example.todobackver2.repository;

import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.entity.Workspace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace,Long> {

    Page<Workspace> findAllByUser(UserEntity userEntity, Pageable pageable);


}
