package com.example.todobackver2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="userName",nullable = false,length = 30)
    private String userName;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="avatar",nullable = true)
    private String avatar;
    @Column(name="email",nullable = true,unique = true)
    private String email;
    @Column(name="accessToken",nullable = true)
    private String accessToken;
    @Column(name="role",nullable = true)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Workspace_user> workspaceUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Project_user> projectUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "userSender")
    private List<ChatEntity> userSenders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Room_user> room_users = new ArrayList<>();

    @OneToMany(mappedBy = "userReceiver")
    private List<ChatEntity> userReceiver = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<GroupChat_User> GroupChat_User = new ArrayList<>();


}
