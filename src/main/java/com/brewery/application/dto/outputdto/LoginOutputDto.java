package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.UUID;

@Data
public class LoginOutputDto {

    private String accessToken;

    private String refreshToken;

    private String expiresIn;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String userName;

    private UUID userId;
}