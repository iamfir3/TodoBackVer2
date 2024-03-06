package com.example.todobackver2.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "workspaceId")
    private Workspace workspace;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = new Date();
    }

    public ProjectEntity(Long id, String projectName, Date dayBegin, Date dayEnd, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.projectName = projectName;
        this.dayBegin = dayBegin;
        DayEnd = dayEnd;
        Description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
