package com.brewery.application.dto.outputdto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class UserOutDto {

    private UUID id;

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private String role;
}
