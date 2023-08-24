package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.inputdto.UserInputDto;
import com.brewery.application.dto.outputdto.LoginOutputDto;
import com.brewery.application.dto.outputdto.SignInFireBaseOutput;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.User;
import com.brewery.application.repository.UserRepository;
import com.brewery.application.service.FireBaseService;
import com.brewery.application.service.UserService;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String FIREBASE_URL="https://identitytoolkit.googleapis.com/v1/accounts";

    private static final String FIREBASE_API_KEY="AIzaSyBonjUFjEXMQNSIcIGX5ekHiP6eWKMWjwg";

    @Autowired
    private FireBaseService fireBaseService;

    @Override
    public UserOutDto createUser(UserInDto input) {
        User user = convertDtoToEntity(input);
        UserInputDto userInput = new UserInputDto();
        userInput.setEmail(user.getEmail());
        userInput.setPassword(user.getPassword());
        userInput.setName(user.getFirstName()+" "+user.getLastName());
        UserRecord userRecord = fireBaseService.createInFireBase(userInput);
        user.setFireBaseId(userRecord.getUid());
        user = userRepository.save(user);
        return convertEntityToDto(user);
    }

    public LoginOutputDto login(LoginInputDto input) {
        System.out.println(FIREBASE_URL);
        System.out.println(FIREBASE_API_KEY);
        String url = FIREBASE_URL+":signInWithPassword?key="+FIREBASE_API_KEY;
        Map<String, Object> map = new HashMap<>();
        map.put("email",input.getEmail());
        map.put("password",input.getPassword());
        map.put("returnSecureToken",true);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(map,httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        String body = exchange.getBody();
        Gson gson = new Gson();
        SignInFireBaseOutput signInFireBaseOutput = gson.fromJson(body,SignInFireBaseOutput.class);

        LoginOutputDto loginOutputDto = new LoginOutputDto();
        loginOutputDto.setAccessToken(signInFireBaseOutput.getIdToken());
        loginOutputDto.setRefreshToken(signInFireBaseOutput.getRefreshToken());
        loginOutputDto.setExpiresIn(signInFireBaseOutput.getExpiresIn());
        return loginOutputDto;
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
