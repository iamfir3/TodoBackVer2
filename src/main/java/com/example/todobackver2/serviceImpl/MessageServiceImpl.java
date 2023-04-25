package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.dto.ChatDto;
import com.example.todobackver2.entity.ChatEntity;
import com.example.todobackver2.entity.RoomChatEntity;
import com.example.todobackver2.entity.Room_user;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.repository.ChatRepository;
import com.example.todobackver2.repository.RoomChatRepository;
import com.example.todobackver2.repository.RoomUserRepository;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    RoomChatRepository roomChatRepository;
    @Autowired
    RoomUserRepository roomUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;
    @Override
    public ChatDto saveMessage(ChatDto chatDto) {
        return null;
    }

    @Override
    public Long takeRoomId(Long userId1, Long userId2) {
        Long roomId=roomUserRepository.findByUserIds(userId1,userId2);
        if(roomId==null){
            RoomChatEntity roomChatEntity=new RoomChatEntity();
            RoomChatEntity newRoom=roomChatRepository.save(roomChatEntity);
            Room_user newRoomUser=new Room_user();
            UserEntity user1=userRepository.findById(userId1).get();
            UserEntity user2=userRepository.findById(userId2).get();
            newRoomUser.setRoom(newRoom);
            newRoomUser.setUserId(userId1);
            newRoomUser.setRoomId(newRoom.getId());
            newRoomUser.setUser(user1);
            roomUserRepository.save(newRoomUser);
            newRoomUser.setUserId(userId2);
            newRoomUser.setRoomId(newRoom.getId());
            newRoomUser.setRoom(newRoom);
            newRoomUser.setUser(user2);
            roomUserRepository.save(newRoomUser);
            roomId=newRoom.getId();
        }
        return roomId;
    }

    @Override
    public String takeRecentMess(Long roomId) {
        RoomChatEntity room=roomChatRepository.findById(roomId).get();
        ChatEntity chatEntity=chatRepository.findByRoomId(room);
        if(chatEntity==null) return "";
         String recentMess=chatRepository.findFirstByRoomIdOrderByCreatedAtDesc(room).getContent();
         if(recentMess!=null)
         {
             return recentMess;
         } else return "";

    }
}
