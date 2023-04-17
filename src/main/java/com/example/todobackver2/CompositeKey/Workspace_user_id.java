package com.example.todobackver2.CompositeKey;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Workspace_user_id implements Serializable {

    private Long userId;
    private Long workspaceId;
}
