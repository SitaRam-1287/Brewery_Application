package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.Role;
import lombok.Data;

import java.util.UUID;

@Data
public class LoginOutputDto {

    private String accessToken;

    private String refreshToken;

    private String expiresIn;

    private Role role;

    private String userName;

    private UUID userId;
}