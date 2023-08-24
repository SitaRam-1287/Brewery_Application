package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.UserInputDto;
import com.brewery.application.service.FireBaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class FireBaseServiceImpl implements FireBaseService {

    @Override
    public UserRecord createInFireBase(UserInputDto input) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(input.getEmail())
                .setPassword(input.getPassword())
                .setDisplayName(input.getName())
                .setDisabled(false);
        try{
            return FirebaseAuth.getInstance().createUser(request);
        }
        catch(FirebaseAuthException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
