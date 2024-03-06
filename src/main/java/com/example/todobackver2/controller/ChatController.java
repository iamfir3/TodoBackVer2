package com.example.todobackver2.controller;


import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.dto.ChatMessagesDto;
import com.example.todobackver2.dto.NotificationDto;
import com.example.todobackver2.entity.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public ChatDto receive(@Payload ChatDto message) throws Exception {
        simpMessagingTemplate.convertAndSendToUser(Long.toString(message.getRoomId()), "/private", message);
        return message;
    }

    @MessageMapping("/messageUserId")
    public ChatMessagesDto receiveUserId(@Payload ChatMessagesDto messagesDto ){
        simpMessagingTemplate.convertAndSendToUser(Long.toString(messagesDto.getUserReceiver()),"/userId",messagesDto);
        return messagesDto;
    }
}