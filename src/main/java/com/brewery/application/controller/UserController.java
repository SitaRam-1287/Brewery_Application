package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController{

    @Autowired
    private UserService userService;

    public UserOutDto createUser(UserInDto input){
        return userService.createUser(input);
    }

    public UserOutDto getUser(UUID id){
        return userService.getUser(id);
    }

    public UserOutDto updateUser(UserInDto input){
        return userService.updateUser(input);
    }

    public UserOutDto partialUpdateUser(UserInDto input){
        return userService.partialUpdateUser(input);
    }

    public List<UserOutDto> getAllUsers(){
        return userService.getAllUsers();
    }

    public UserOutDto deleteUser(UUID id){
        return userService.deleteUser(id);
    }
}
