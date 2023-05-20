package com.example.todobackver2.service;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.dto.ChatDto;

import java.util.List;

public interface MessageService {

    ChatDto saveMessage(ChatDto chatDto);

    Long takeRoomId(Long userId1, Long userId2);

    String takeRecentMess(Long roomId);

    List<ChatDto> getAllMessage(Long roomId);

    List<AuthDto> getOtherPerson(Long roomId, Long userCurrent);
}
