package com.example.todobackver2.entity;

import com.example.todobackver2.CompositeKey.Project_user_id;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(Project_user_id.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class Project_user {

    @Id
    private Long userId;
    @Id
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "userId",insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "projectId", insertable = false, updatable = false)
    private ProjectEntity project;
}
