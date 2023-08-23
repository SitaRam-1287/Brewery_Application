package com.brewery.application.dto.inputdto;

import com.brewery.application.enums.Role;
import lombok.Data;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private Role role;

}
