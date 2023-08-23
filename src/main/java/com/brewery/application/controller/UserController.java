package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserOutDto createUser(@RequestBody UserInDto input){
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
