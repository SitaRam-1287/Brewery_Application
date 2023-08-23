package com.brewery.application.dto.inputdto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private String role;
}
