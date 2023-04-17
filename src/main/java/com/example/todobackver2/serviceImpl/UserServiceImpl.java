package com.example.todobackver2.serviceImpl;

import com.example.todobackver2.Exception.AppExceptionHandler;
import com.example.todobackver2.Exception.ErrorMessage;
import com.example.todobackver2.Exception.UserServiceExceptions;
import com.example.todobackver2.dto.AuthDto;
import com.example.todobackver2.entity.UserEntity;
import com.example.todobackver2.repository.UserRepository;
import com.example.todobackver2.response.ErrorMessageResponse;
import com.example.todobackver2.security.SecurityContants;
import com.example.todobackver2.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public AuthDto getUser(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null) throw new UserServiceExceptions(ErrorMessage.NOT_VALID_EMAIL.getErrorMessage(), ErrorMessage.NOT_VALID_EMAIL.getStatus());
        AuthDto returnValue=new AuthDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public AuthDto getUserByAccessToken(String accessToken) {
        accessToken=accessToken.replace(SecurityContants.TOKEN_PREFIX,"");
        UserEntity userEntity=userRepository.findByAccessToken(accessToken);
        if(userEntity==null) throw new UserServiceExceptions(ErrorMessage.TOKEN_INVALID.getErrorMessage(), ErrorMessage.TOKEN_INVALID.getStatus());
        AuthDto returnValue=new AuthDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserEntity getUserById(String userId) {
        UserEntity returnValue=userRepository.findById(userId);
        return returnValue;
    }

    @Override
    public void updateUserById(String userId, UserEntity userEntity) {
        UserEntity user=userRepository.findById(userId);
        userRepository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


}