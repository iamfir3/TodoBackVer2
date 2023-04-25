package com.example.todobackver2.CompositeKey;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Room_user_id implements Serializable {
    private Long userId;
    private Long roomId;
}
