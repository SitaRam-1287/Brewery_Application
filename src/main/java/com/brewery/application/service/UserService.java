package com.brewery.application.service;

import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.LoginOutputDto;
import com.brewery.application.dto.outputdto.UserOutDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public LoginOutputDto login(LoginInputDto input);

    public UserOutDto createUser(UserInDto input);

    public UserOutDto getUser(UUID id);

    public UserOutDto updateUser(UserInDto input);

    public UserOutDto partialUpdateUser(UserInDto input);

    public List<UserOutDto> getAllUsers();

    public UserOutDto deleteUser(UUID id);

}
