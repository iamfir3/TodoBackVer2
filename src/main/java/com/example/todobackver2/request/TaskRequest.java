package com.example.todobackver2.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
        private String taskName;
        private Date dayBegin;
        private Date dayEnd;
        private String description;
        private String projectId;
}
