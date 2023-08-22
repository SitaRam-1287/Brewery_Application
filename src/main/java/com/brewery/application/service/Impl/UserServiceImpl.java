package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.User;
import com.brewery.application.repository.UserRepository;
import com.brewery.application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserOutDto createUser(UserInDto input) {
        User user = convertDtoToEntity(input);
        user = userRepository.save(user);
        return convertEntityToDto(user);
    }

    @Override
    public UserOutDto getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User with given id is not found"));
        return convertEntityToDto(user);
    }

    @Override
    public UserOutDto updateUser(UserInDto input) {
        User user = convertDtoToEntity(input);
        User existingUser = userRepository.findById(user.getId()).orElseThrow();
        modelMapper.map(user,existingUser);
        User currentUser = userRepository.save(existingUser);
        return convertEntityToDto(currentUser);
    }

    @Override
    public UserOutDto partialUpdateUser(UserInDto input) {
        return null;
    }

    @Override
    public List<UserOutDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserOutDto> userList = users.stream().map(user -> convertEntityToDto(user)).collect(Collectors.toList());
        return userList;
    }

    @Override
    public UserOutDto deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with given Id"));
        userRepository.delete(user);
        return convertEntityToDto(user);
    }

    public User convertDtoToEntity(UserInDto input){
        return modelMapper.map(input,User.class);
    }

    public UserOutDto convertEntityToDto(User user){
        return modelMapper.map(user,UserOutDto.class);
    }


}
