package com.example.todobackver2.repository;

import com.example.todobackver2.CompositeKey.Room_user_id;
import com.example.todobackver2.entity.RoomChatEntity;
import com.example.todobackver2.entity.Room_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomUserRepository extends JpaRepository<Room_user, Room_user_id> {
    @Query(value = "SELECT room_id FROM room_user " +
            "WHERE user_id = ?1 AND room_id IN (" +
            "  SELECT room_id FROM room_user WHERE user_id = ?2" +
            ")", nativeQuery = true)
    Long findByUserIds(Long userId1, Long userId2);

    List<Room_user> findAllByRoomId(Long roomId);
}
