package com.brewery.application.service;

import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.dto.outputdto.LoginOutputDto;
import com.brewery.application.dto.outputdto.UserFullDetailsDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.Address;
import com.brewery.application.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public LoginOutputDto login(LoginInputDto input);

    public UserOutDto createUser(UserInDto input);

    public UserFullDetailsDto getUser(UUID id);

    public String verifyEmail(UUID userId);

    String resetPassword(UUID userId);

    public byte[] postImage(@RequestHeader UUID id, MultipartFile image);

    boolean verifyOtp(String otp);

    public UserOutDto updateAddress(Address address, UUID id);

    public List<AddressOutDto> getAddressList(UUID id);

    public UserOutDto updateUser(UserInDto input);

    public UserOutDto partialUpdateUser(UUID userId,UserInDto input);

    public List<UserOutDto> getAllUsers();

    public UserOutDto deleteUser(UUID id);

}
