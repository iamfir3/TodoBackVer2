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
public class GroupChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="amount",nullable = false)
    private int amount;

    @OneToMany(mappedBy = "groupChatEntity")
    private List<GroupChat_Content> groupChatEntities = new ArrayList<>();

    @OneToMany(mappedBy = "groupChatEntity")
    private List<GroupChat_User> groupChat_users = new ArrayList<>();
}
