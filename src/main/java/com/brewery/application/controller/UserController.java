package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @PostMapping
    public UserOutDto createUser(@RequestBody UserInDto input){
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
