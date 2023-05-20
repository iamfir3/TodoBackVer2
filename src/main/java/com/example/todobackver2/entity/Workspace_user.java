package com.example.todobackver2.entity;


import com.example.todobackver2.CompositeKey.Workspace_user_id;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

@Entity
@IdClass(Workspace_user_id.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class Workspace_user {

    @Id
    private Long userId;

    @Id
    private Long workspaceId;

    @ManyToOne
    @JoinColumn(name = "userId",insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "workspaceId",insertable = false, updatable = false)
    private Workspace workspace;

}
