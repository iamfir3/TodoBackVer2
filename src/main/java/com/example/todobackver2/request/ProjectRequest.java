package com.example.todobackver2.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private String projectName;
    private Date dayBegin;
    private Date dayEnd;
    private String description;
    private String workspaceId;
}
