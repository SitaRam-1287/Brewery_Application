package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 62de3ab155bd75d771c94a565561bd7c6bb138aa

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

<<<<<<< HEAD
    @PostMapping()
    public UserOutDto createUser(@RequestBody UserInDto input){
=======
    @PostMapping
    public UserOutDto createUser(@RequestBody UserInDto input){

>>>>>>> 62de3ab155bd75d771c94a565561bd7c6bb138aa
        return userService.createUser(input);
    }

    @GetMapping
    public UserOutDto getUser(UUID id){
        return userService.getUser(id);
    }

    @PutMapping
    public UserOutDto updateUser(UserInDto input){
        return userService.updateUser(input);
    }


    @PatchMapping
    public UserOutDto partialUpdateUser(UserInDto input){
        return userService.partialUpdateUser(input);
    }

    @GetMapping("/getAll")
    public List<UserOutDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping
    public UserOutDto deleteUser(UUID id){
        return userService.deleteUser(id);
    }
}
