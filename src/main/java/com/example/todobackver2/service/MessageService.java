package com.example.todobackver2.service;

import com.example.todobackver2.dto.ChatDto;

public interface MessageService {

    ChatDto saveMessage(ChatDto chatDto);

    Long takeRoomId(Long userId1, Long userId2);

    String takeRecentMess(Long roomId);
}
