package com.example.todobackver2.repository;

import com.example.todobackver2.entity.RoomChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChatEntity,Long> {

}
