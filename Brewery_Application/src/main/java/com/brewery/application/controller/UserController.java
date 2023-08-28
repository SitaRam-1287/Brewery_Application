package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.dto.outputdto.LoginOutputDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.Address;
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

    @PostMapping("/login")
    public LoginOutputDto login(@RequestBody LoginInputDto loginInputDto){
        return userService.login(loginInputDto);
    }

    @GetMapping("{id}")
    public UserOutDto getUser(@PathVariable UUID id){
        return userService.getUser(id);
    }

    @PutMapping
    public UserOutDto updateUser(UserInDto input){

        return userService.updateUser(input);
    }

    @PostMapping("/address/{id}")
    public UserOutDto updateAddress(@RequestBody Address address,@PathVariable UUID id){
        return null;
    }
    @GetMapping("/address/{id}")
    public List<AddressOutDto> getAddressList(@PathVariable UUID id){
        return userService.getAddressList(id);
    }



    @PatchMapping
    public UserOutDto partialUpdateUser(UserInDto input){
        return userService.partialUpdateUser(input);
    }

    @GetMapping("/getAll")
    public List<UserOutDto> getAllUsers(){
        return userService.getAllUsers();
    }



    @DeleteMapping("{id}")
    public UserOutDto deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
}
