package com.example.todobackver2.dto;

import com.example.todobackver2.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    private String userSender;
    private String userReceiver;
    private String content;
    private String isRead;
    private String roomId;
    private Date time;
    private Status status;
}
