package com.example.todobackver2.CompositeKey;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Project_user_id implements Serializable {

    private Long userId;
    private Long projectId;
}
