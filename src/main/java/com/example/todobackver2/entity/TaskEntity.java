package com.example.todobackver2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "taskName",nullable = false,length = 30)
    private String taskName;
    @Column(name="dayBegin",nullable = false)
    private Date dayBegin;
    @Column(name="dayEnd",nullable = false)
    private Date dayEnd;

    @Column(name="isDone",nullable = false)
    private boolean isDone=false;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="projectId",nullable = false)
    private ProjectEntity project;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @Column(name="ghim",nullable = false)
    private boolean ghim=false;

    @Column(name="priority",nullable = false)
    private int priority;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="userId",nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "task")
    private List<CommentEntity> comments=new ArrayList<>();
}
