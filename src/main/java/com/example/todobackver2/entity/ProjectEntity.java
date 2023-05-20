package com.example.todobackver2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "projectName",nullable = false,length = 30)
    private String projectName;
    @Column(name="dayBegin",nullable = false)
    private Date dayBegin;
    @Column(name="dayEnd",nullable = false)
    private Date DayEnd;
    @Column(name="description",nullable = false,length = 200)
    private String Description;

    @OneToMany(mappedBy = "project")
    private List<Project_user> projectUsers = new ArrayList<>();
    @OneToMany(mappedBy="project")
    private List<TaskEntity> taskEntities=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "workspaceId",insertable = false, updatable = false)
    private Workspace workspace;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new Date();
    }
}
