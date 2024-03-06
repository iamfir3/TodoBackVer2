package com.example.todobackver2.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
        private String taskName;
        private Date dayBegin;
        private Date dayEnd;
        private String description;
        private Long projectId;
        private Long userId;
        private int priority;
}
