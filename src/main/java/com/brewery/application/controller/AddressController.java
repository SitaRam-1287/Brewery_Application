package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.AddressInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @PostMapping
    private AddressOutDto postAddress(@RequestBody AddressInDto addressInDto){
        return addressService.postAddress(addressInDto);
    }

    @GetMapping("/{id}")
    private AddressOutDto getAddressById(@PathVariable UUID id){
        return addressService.getAddressById(id);
    }

    @GetMapping
    private List<AddressOutDto> getAddress(){
        return addressService.getAllAddress();
    }

    @PutMapping
    private AddressOutDto updateAddress(@RequestBody AddressInDto addressInDto){
        return addressService.updateAddress(addressInDto);
    }

    @PatchMapping
    private AddressOutDto patchAddress(@RequestBody AddressInDto addressInDto){
        return addressService.patchAddress(addressInDto);
    }

    @DeleteMapping("/{id}")
    private void deleteAddressById(@PathVariable UUID id){
        addressService.deleteAddressById(id);
    }

    @DeleteMapping
    private void deleteAddress(){
        addressService.deleteAddress();
    }
}
