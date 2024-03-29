package com.example.todobackver2.dto;

import com.example.todobackver2.entity.Status;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatDto {
    private Long userSender;
    private Long userReceiver;
    private String content;
    private String isRead;
    private Long roomId;
    private Date time;
    private Status status;
}
