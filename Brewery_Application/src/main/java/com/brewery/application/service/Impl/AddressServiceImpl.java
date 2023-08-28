package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.AddressInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.entity.Address;
import com.brewery.application.repository.AddressRepository;
import com.brewery.application.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public AddressOutDto postAddress(AddressInDto addressInDto) {
        Address address=convertDtoToEntity(addressInDto);
        Address address1=addressRepository.save(address);
        return convertEntityToDto(address1);
    }

    @Override
    public AddressOutDto getAddressById(UUID id) {
        Address address=addressRepository.findById(id).orElseThrow(()->new RuntimeException("Address with id not found"));
        return convertEntityToDto(address);
    }

    @Override
    public List<AddressOutDto> getAllAddress() {
        List<Address> addresses=addressRepository.findAll();
        return addresses.stream().map(address->convertEntityToDto(address)).collect(Collectors.toList());
    }

    @Override
    public AddressOutDto updateAddress(AddressInDto addressInDto) {
        Address address=convertDtoToEntity(addressInDto);
        Address address1=addressRepository.findById(address.getId()).orElseThrow(()->new RuntimeException("Address with id not found"));
        modelMapper.map(address1,address);
        Address address2=addressRepository.save(address1);
        return convertEntityToDto(address2);
    }

    @Override
    public AddressOutDto patchAddress(AddressInDto addressInDto) {
        return null;
    }

    @Override
    public void deleteAddressById(UUID id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void deleteAddress() {
        addressRepository.deleteAll();
    }

    public Address convertDtoToEntity(AddressInDto addressInDto){
        return modelMapper.map(addressInDto, Address.class);
    }

    public AddressOutDto convertEntityToDto(Address address){
        return modelMapper.map(address,AddressOutDto.class);
    }
}
