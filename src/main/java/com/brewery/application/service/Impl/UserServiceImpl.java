package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.User;
import com.brewery.application.repository.UserRepository;
import com.brewery.application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public User createUser(UserInDto input) {
        User user = convertDtoToEntity(input);
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if(user==null){
            throw new RuntimeException("ID with given user doesn't exist");
        }
        return
    }

    @Override
    public User updateUser(UserInDto input) {
        return null;
    }

    @Override
    public User partialUpdateUser(UserInDto input) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User deleteUser(UUID id) {
        return null;
    }

    public User convertDtoToEntity(UserInDto input){
        return modelMapper.map(input,User.class);
    }

    public User convertEntityToDto(UserOutDto output){
        return modelMapper.map(output,User.class);
    }


}
