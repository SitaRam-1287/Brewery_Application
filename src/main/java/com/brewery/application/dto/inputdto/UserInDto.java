package com.brewery.application.dto.inputdto;

<<<<<<< HEAD
import com.brewery.application.entity.Address;
import lombok.Data;

import java.util.List;

=======
import com.brewery.application.enums.Role;
import lombok.Data;

>>>>>>> 8fe2e7b345237b28d8411cc86ce6c4664291adcb
@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

<<<<<<< HEAD
    private String role;

    private List<Address> addressList;
=======
    private Role role;

>>>>>>> 8fe2e7b345237b28d8411cc86ce6c4664291adcb
}
