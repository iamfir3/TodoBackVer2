package com.example.todobackver2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="roomName",nullable = true)
    private String roomName;

    @Column(name="type")
    private int type;

    @OneToMany(mappedBy = "room")
    private List<Room_user> room_users = new ArrayList<>();

    @OneToMany(mappedBy = "roomId")
    private List<ChatEntity> chatEntity = new ArrayList<>();
}
