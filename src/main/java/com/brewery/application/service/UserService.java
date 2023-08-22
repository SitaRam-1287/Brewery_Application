package com.brewery.application.service;

import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public User createUser(UserInDto input);

    public User getUser(UUID id);

    public User updateUser(UserInDto input);

    public User partialUpdateUser(UserInDto input);

    public List<User> getAllUsers();

    public User deleteUser(UUID id);

}
