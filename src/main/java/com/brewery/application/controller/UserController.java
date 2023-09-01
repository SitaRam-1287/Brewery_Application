package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.dto.outputdto.LoginOutputDto;
import com.brewery.application.dto.outputdto.UserFullDetailsDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.Address;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping()
    public UserFullDetailsDto getUser(@RequestHeader UUID userId){
        return userService.getUser(userId);
    }

    @PutMapping
    public UserOutDto updateUser(UserInDto input){

        return userService.updateUser(input);
    }

    @PostMapping(value="/image",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] postImage(@RequestHeader UUID id, @RequestParam MultipartFile image){
        return userService.postImage(id,image);
    }

    @PostMapping("/address")
    public UserOutDto updateAddress(@RequestBody Address address,@RequestHeader UUID id){
        return userService.updateAddress(address,id);
    }
    @GetMapping("/address")
    public List<AddressOutDto> getAddressList(@RequestHeader UUID id){
        return userService.getAddressList(id);
    }


    @PatchMapping
    public UserOutDto partialUpdateUser(@RequestHeader UUID userId , UserInDto input){
        return userService.partialUpdateUser(userId,input);
    }

    @GetMapping("/getAll")
    public List<UserOutDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/email")
    public String verifyEmail(@RequestHeader UUID userId){
        return userService.verifyEmail(userId);
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestHeader UUID userId){
        return userService.resetPassword(userId);
    }

    @PostMapping("/otp")
    public boolean verifyOtp(@RequestParam String otp){
        return userService.verifyOtp(otp);
    }
    @DeleteMapping("{id}")
    public UserOutDto deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }
}
