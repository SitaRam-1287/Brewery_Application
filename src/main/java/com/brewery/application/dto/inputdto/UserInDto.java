package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.Address;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.brewery.application.enums.Role;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNum;

    private List<AddressInDto> addressList;

    private LocalDateTime dateOfBirth;

    private Role role;
}
