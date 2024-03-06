package com.example.todobackver2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagesDto {
    private Long roomId;
    private String content;
    private int countUnread;
    private Long userSender;
    private Long userReceiver;
}
