package com.brewery.application.dto.inputdto;

import com.brewery.application.enums.RoleType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private RoleType role;
}
