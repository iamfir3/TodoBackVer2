package com.example.todobackver2.repository;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findById(String userId);

    UserEntity findByAccessToken(String accessToken);
}
