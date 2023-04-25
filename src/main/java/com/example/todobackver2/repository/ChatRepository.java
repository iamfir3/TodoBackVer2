package com.example.todobackver2.repository;

import com.example.todobackver2.entity.ChatEntity;
import com.example.todobackver2.entity.RoomChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity,Long> {
    ChatEntity findFirstByRoomIdOrderByCreatedAtDesc(RoomChatEntity room);

    ChatEntity findByRoomId(RoomChatEntity room);
}
