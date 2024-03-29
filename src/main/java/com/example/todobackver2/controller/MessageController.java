package com.example.todobackver2.controller;

import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.dto.ChatMessagesDto;
import com.example.todobackver2.entity.ChatEntity;
import com.example.todobackver2.entity.Room_user;
import com.example.todobackver2.response.MessageResponse;
import com.example.todobackver2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    @CrossOrigin
    public ChatDto saveMessage(@RequestBody ChatDto chatDto){
        ChatDto messageResponse=messageService.saveMessage(chatDto);
        return messageResponse;
    }

    @GetMapping("/{roomId}")
    @CrossOrigin
    public List<ChatDto> getAllMessage(@PathVariable Long roomId)
    {
        List<ChatDto> messages=messageService.getAllMessage(roomId);
        return messages;
    }

    @GetMapping("/getOtherPerson/{roomId}/{userCurrent}")
    @CrossOrigin
    public List<AuthDto> getOtherPerson(@PathVariable Long roomId,@PathVariable Long userCurrent)
    {
        List<AuthDto> authDtos=messageService.getOtherPerson(roomId,userCurrent);
        return authDtos;
    }


    @GetMapping("/takeRoomId/{userId1}/{userId2}")
    @CrossOrigin
    public Long takeRoomId(@PathVariable Long userId1, @PathVariable Long userId2)
    {
        Long roomId=messageService.takeRoomId(userId1,userId2);
        return roomId;
    }

    @GetMapping("/takeRecentMess/{roomId}/{userId}")
    @CrossOrigin
    public ChatMessagesDto takeRecentMess(@PathVariable Long roomId,@PathVariable Long userId)
    {
        return messageService.takeRecentMess(roomId,userId);
    }

    @GetMapping("/takeUnreadCount/{roomId}/{userId}")
    @CrossOrigin
    public ChatMessagesDto takeUnreadCount(@PathVariable Long roomId,@PathVariable Long userId){
        return messageService.takeUnreadCount(roomId,userId);
    }

    @PutMapping("/resetUnreadCount/{roomId}/{userId}")
    @CrossOrigin
    public ChatMessagesDto resetUnreadCount (@PathVariable Long roomId,@PathVariable Long userId){
        return  messageService.resetUnreadCount(roomId,userId);
    }

}
