package com.example.todobackver2.controller;

import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.response.MessageResponse;
import com.example.todobackver2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    @CrossOrigin
    public MessageResponse saveMessage(@RequestBody ChatDto chatDto){
        MessageResponse messageResponse=new MessageResponse();

        return messageResponse;
    }

    @GetMapping("/takeRoomId/{userId1}/{userId2}")
    @CrossOrigin
    public Long takeRoomId(@PathVariable Long userId1, @PathVariable Long userId2)
    {
        Long roomId=messageService.takeRoomId(userId1,userId2);
        return roomId;
    }

    @GetMapping("/takeRecentMess/{roomId}")
    @CrossOrigin
    public String takeRecentMess(@PathVariable Long roomId)
    {
        String recentMess=messageService.takeRecentMess(roomId);
        return recentMess;
    }

}
