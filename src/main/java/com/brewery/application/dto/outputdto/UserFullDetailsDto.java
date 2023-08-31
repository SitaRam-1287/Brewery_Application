package com.brewery.application.dto.outputdto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserFullDetailsDto {

    private UUID id;

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private String image;

    private LocalDate dateOfBirth;
}
