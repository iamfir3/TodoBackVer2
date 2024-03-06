package com.example.todobackver2.service;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.dto.ChatMessagesDto;

import java.util.List;

public interface MessageService {

    ChatDto saveMessage(ChatDto chatDto);

    Long takeRoomId(Long userId1, Long userId2);

    ChatMessagesDto takeRecentMess(Long roomId,Long userId);

    List<ChatDto> getAllMessage(Long roomId);

    List<AuthDto> getOtherPerson(Long roomId, Long userCurrent);

    ChatMessagesDto takeUnreadCount (Long roomId, Long userId);

    ChatMessagesDto resetUnreadCount(Long roomId, Long userId);
}
