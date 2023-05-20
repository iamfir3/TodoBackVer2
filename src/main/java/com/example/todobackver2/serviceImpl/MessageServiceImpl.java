package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.dto.AuthDto;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        ChatEntity chatEntity=new ChatEntity();
        BeanUtils.copyProperties(chatDto,chatEntity);
        UserEntity userSender= userRepository.findById(chatDto.getUserSender()).get();
        UserEntity userReceiver=userRepository.findById(chatDto.getUserSender()).get();
        RoomChatEntity room=roomChatRepository.findById(chatDto.getRoomId()).get();
        chatEntity.setUserSender(userSender);
        chatEntity.setUserReceiver(userReceiver);
        chatEntity.setRead(false);
        chatEntity.setRoomId(room);
        ChatEntity storedChat= chatRepository.save(chatEntity);
        BeanUtils.copyProperties(storedChat,chatDto);
        return chatDto;
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
        List<ChatEntity> chatEntity=chatRepository.findAllByRoomId(room);
        if(chatEntity.size()==0) return "";
         String recentMess=chatRepository.findFirstByRoomIdOrderByCreatedAtDesc(room).getContent();
         if(recentMess!=null)
         {
             return recentMess;
         } else return "";

    }

    @Override
    public List<ChatDto> getAllMessage(Long roomId) {
        RoomChatEntity room=roomChatRepository.findById(roomId).get();
        List<ChatEntity> messages=chatRepository.findAllByRoomId(room);
        List<ChatDto> returnValue=new ArrayList<>();
        for(ChatEntity message:messages){
            ChatDto chatDto=new ChatDto();
            BeanUtils.copyProperties(message,chatDto);
            chatDto.setUserReceiver(message.getUserReceiver().getId());
            chatDto.setUserSender(message.getUserSender().getId());
            chatDto.setTime(message.getCreatedAt());
            chatDto.setRoomId(message.getRoomId().getId());
            returnValue.add(chatDto);
        }

        return returnValue;
    }

    @Override
    public List<AuthDto> getOtherPerson(Long roomId, Long userCurrent) {
        RoomChatEntity room=roomChatRepository.findById(roomId).get();
        List<Room_user> room_users=roomUserRepository.findAllByRoomId(room.getId());
        List<AuthDto> authDtos=new ArrayList<>();
        for(Room_user room_user:room_users){
            if(room_user.getUserId()!=userCurrent){
                AuthDto authDto=new AuthDto();
                UserEntity user=userRepository.findById(room_user.getUserId()).get();
                BeanUtils.copyProperties(user,authDto);
                authDtos.add(authDto);
            }
        }
        return authDtos;
    }
}
