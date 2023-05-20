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
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="workspaceName",nullable = false,length = 30)
    private String workspaceName;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="userId",nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "workspace")
    private List<Workspace_user> workspaceUsers = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    private List<ProjectEntity> projects = new ArrayList<>();
}
