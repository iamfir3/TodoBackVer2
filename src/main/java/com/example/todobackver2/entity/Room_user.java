package com.example.todobackver2.entity;

import com.example.todobackver2.CompositeKey.Room_user_id;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(Room_user_id.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class Room_user {
    @Id
    private Long userId;

    @Id
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "userId",insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roomId",insertable = false, updatable = false)
    private RoomChatEntity room;
}
