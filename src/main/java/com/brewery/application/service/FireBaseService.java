package com.brewery.application.service;

import com.brewery.application.dto.inputdto.UserInputDto;
import com.google.firebase.auth.UserRecord;

public interface FireBaseService {
    public UserRecord createInFireBase(UserInputDto userInput);
}
