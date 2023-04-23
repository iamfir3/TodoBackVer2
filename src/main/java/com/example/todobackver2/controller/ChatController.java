package com.example.todobackver2.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ChatController {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @MessageMapping("/chat/{username}")
//    public void sendMessage(@DestinationVariable("username") String username, ChatMessage message) {
//        rabbitTemplate.convertAndSend("amq.topic", "user." + username, message);
//    }
//
//    @SubscribeMapping("/chat/{username}")
//    public List<ChatMessage> getMessages(@DestinationVariable("username") String username) {
//        return Collections.emptyList();
//    }
}