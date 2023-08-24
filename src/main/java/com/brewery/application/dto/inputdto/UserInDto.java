package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.Address;
import lombok.Data;
import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 8266bbc618bbaffe807f5e9ed00485e8c0a253b7
import com.brewery.application.enums.Role;

@Data
public class UserInDto {

    private  String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private List<Address> addressList;
<<<<<<< HEAD

=======
>>>>>>> 8266bbc618bbaffe807f5e9ed00485e8c0a253b7
    private Role role;
}
