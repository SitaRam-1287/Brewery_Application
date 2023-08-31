package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.AddressInDto;
import com.brewery.application.dto.inputdto.LoginInputDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.inputdto.UserInputDto;
import com.brewery.application.dto.outputdto.*;
import com.brewery.application.entity.Address;
import com.brewery.application.entity.User;
import com.brewery.application.enums.Role;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.AddressRepository;
import com.brewery.application.repository.StoreRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    private static final String FIREBASE_URL="https://identitytoolkit.googleapis.com/v1/accounts";

    private static final String FIREBASE_API_KEY="AIzaSyBonjUFjEXMQNSIcIGX5ekHiP6eWKMWjwg";

    @Autowired
    private FireBaseService fireBaseService;

    @Override
    public UserOutDto createUser(UserInDto input) {
        System.out.println(input.getPhoneNum());
        int age = LocalDate.now().getYear() - input.getDateOfBirth().getYear();
        if(age<21){
            throw new ElementNotFoundException("Access Restricted for users under age 18");
        }
        User user = convertDtoToEntity(input);
        UserInputDto userInput = new UserInputDto();
        userInput.setEmail(user.getEmail());
        userInput.setPassword(input.getPassword());
        userInput.setName(user.getFirstName()+" "+user.getLastName());
        UserRecord userRecord = fireBaseService.createInFireBase(userInput);
        user.setFireBaseId(userRecord.getUid());

        List<AddressInDto> address = input.getAddressList();
        List<Address> addressList = new ArrayList<>();
        List<AddressOutDto> addressList2  = new ArrayList<>();
        if(address!=null){
        for(AddressInDto address1 : address){
            Address address2 = modelMapper.map(address1,Address.class);
            address2 = addressRepository.save(address2);
            addressList.add(address2);
            addressList2.add(modelMapper.map(address2,AddressOutDto.class));
        }}
        user.setAddressList(addressList);
        user.setRole(Role.USER);
        user = userRepository.save(user);
        System.out.println(user.getPhoneNum());
        UserOutDto userOutDto = convertEntityToDto(user);
        userOutDto.setAddressList(addressList2);
        return userOutDto;
    }

    public LoginOutputDto login(LoginInputDto input) {
        System.out.println(FIREBASE_URL);
        System.out.println(FIREBASE_API_KEY);
        String url = FIREBASE_URL+":signInWithPassword?key="+FIREBASE_API_KEY;
        Map<String, Object> map = new HashMap<>();
        map.put("email",input.getEmail());
        map.put("password",input.getPassword());
        map.put("returnSecureToken",true);

        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity<?> httpEntity = new HttpEntity<>(map, httpHeaders);
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            String body = exchange.getBody();
            Gson gson = new Gson();

            User user = userRepository.findByEmail(input.getEmail());
            SignInFireBaseOutput signInFireBaseOutput = gson.fromJson(body, SignInFireBaseOutput.class);

            LoginOutputDto loginOutputDto = new LoginOutputDto();
            loginOutputDto.setAccessToken(signInFireBaseOutput.getIdToken());
            loginOutputDto.setRefreshToken(signInFireBaseOutput.getRefreshToken());
            loginOutputDto.setExpiresIn(signInFireBaseOutput.getExpiresIn());
            loginOutputDto.setUserId(user.getId());
            loginOutputDto.setUserName(user.getFirstName() + " " + user.getLastName());
            return loginOutputDto;
        }
        catch(Exception e){
            throw new ElementNotFoundException("Please enter valid email and password");
        }
    }

    @Override
    public UserFullDetailsDto getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(()->new ElementNotFoundException("User with given id is not found"));
        return modelMapper.map(user,UserFullDetailsDto.class);
    }

    @Override
    public String verifyEmail(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ElementNotFoundException("User with given id is not found"));
        return emailService.sendEmail(user.getEmail());
    }

    @Override
    public byte[] postImage(UUID id, MultipartFile image) {
        User user = userRepository.findById(id).orElseThrow(()->new ElementNotFoundException("User with given id is not found"));
        try {
            byte[] arr = image.getBytes();
            String s = Base64.getEncoder().encodeToString(arr);
            user.setImage(s);
            userRepository.save(user);
            return arr;
        }
        catch(Exception e){
            throw new ElementNotFoundException("error posting image");
        }
    }

    @Override
    public boolean verifyOtp(String otp){
        return emailService.verifyOtp(otp);
    }

    @Override
    public UserOutDto updateAddress(Address address, UUID id) {
        User user=userRepository.findById(id).orElseThrow(()->new ElementNotFoundException("ID not found"));
        Address address1=addressRepository.save(address);
        List<Address> addressList = user.getAddressList();
        if(addressList!=null){
            addressList.add(address1);
            user.setAddressList(addressList);
        }
        else{
            user.setAddressList(List.of(address1));
        }
        user = userRepository.save(user);
        return convertEntityToDto(user);
    }

    @Override
    public List<AddressOutDto> getAddressList(UUID id) {
        User user=userRepository.findById(id).orElseThrow(()->new ElementNotFoundException("user with id not found"));
        List<Address> addressList=user.getAddressList();
        return addressList.stream().map(address -> modelMapper.map(address,AddressOutDto.class)).collect(Collectors.toList());


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
        List<UserOutDto> userList = users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return userList;
    }

    @Override
    public UserOutDto deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("User not found with given Id"));
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
