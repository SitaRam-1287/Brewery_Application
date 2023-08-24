package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class UserOutDto {

    private UUID id;

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private Role role;
}
