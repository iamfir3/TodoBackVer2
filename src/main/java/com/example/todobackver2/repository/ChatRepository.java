package com.example.todobackver2.repository;

import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.entity.ChatEntity;
import com.example.todobackver2.entity.RoomChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity,Long> {
    ChatEntity findFirstByRoomIdOrderByCreatedAtDesc(RoomChatEntity room);

    ChatEntity findByRoomId(RoomChatEntity room);

    List<ChatEntity> findAllByRoomId(RoomChatEntity room);
}
