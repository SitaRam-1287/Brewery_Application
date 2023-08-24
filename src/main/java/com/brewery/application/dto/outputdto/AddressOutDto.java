package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.AddressType;
import lombok.Data;

@Data
public class AddressOutDto {
    private String dNo;

    private String street;

    private String city;

    private String state;

    private String pin;

    private AddressType addressType;
}
