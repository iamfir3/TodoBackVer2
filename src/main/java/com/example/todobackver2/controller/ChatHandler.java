package com.example.todobackver2.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.todobackver2.entity.ChatEntity;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatEntity chatMessage = objectMapper.readValue(message.getPayload(), ChatEntity.class);
        WebSocketSession recipientSession = sessions.get(chatMessage.getUserReceiver());
        if (recipientSession != null && recipientSession.isOpen()) {
            recipientSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }
}
