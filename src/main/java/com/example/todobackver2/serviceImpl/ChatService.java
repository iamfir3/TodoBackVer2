//package com.example.todobackver2.serviceImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.SubscribeMapping;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class ChatService {
//
//    private final SimpMessagingTemplate messagingTemplate;
//
//    private final Map<String, List<String>> chatSessions = new ConcurrentHashMap<>();
//
//    @Autowired
//    public ChatService(SimpMessagingTemplate messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    public void sendMessage(String chatId, String sender, String message) {
//        messagingTemplate.convertAndSend("/topic/chat/" + chatId, new ChatMessage(sender, message));
//    }
//
//    @SubscribeMapping("/chat/{chatId}/join")
//    public void joinChat(@DestinationVariable String chatId, SimpMessageHeaderAccessor headerAccessor) {
//        String sessionId = headerAccessor.getSessionId();
//        String username = headerAccessor.getUser().getName();
//
//        chatSessions.computeIfAbsent(chatId, k -> new ArrayList<>()).add(sessionId);
//
//        messagingTemplate.convertAndSend("/topic/chat/" + chatId, new ChatMessage(username, "joined"));
//    }
//
//    @SubscribeMapping("/chat/{chatId}/leave")
//    public void leaveChat(@DestinationVariable String chatId, SimpMessageHeaderAccessor headerAccessor) {
//        String sessionId = headerAccessor.getSessionId();
//        String username = headerAccessor.getUser().getName();
//
//        List<String> sessions = chatSessions.get(chatId);
//        if (sessions != null) {
//            sessions.remove(sessionId);
//            if (sessions.isEmpty()) {
//                chatSessions.remove(chatId);
//            }
//        }
//
//        messagingTemplate.convertAndSend("/topic/chat/" + chatId, new ChatMessage(username, "left"));
//    }
//
//}
