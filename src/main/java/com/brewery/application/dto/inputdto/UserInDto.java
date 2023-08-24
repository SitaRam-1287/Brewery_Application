package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private String role;

    private List<Address> addressList;
}
