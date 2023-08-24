package com.brewery.application.service;

import com.brewery.application.dto.inputdto.AddressInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {

    public AddressOutDto postAddress(AddressInDto addressInDto);

    public AddressOutDto getAddressById(UUID id);

    public List<AddressOutDto> getAllAddress();

    public AddressOutDto updateAddress(AddressInDto addressInDto);

    public AddressOutDto patchAddress(AddressInDto addressInDto);

    public void deleteAddressById(UUID id);

    public void deleteAddress();
}
