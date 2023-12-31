package com.brewery.application.service.Impl;


import com.brewery.application.entity.Address;
import com.brewery.application.entity.Store;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.AddressRepository;
import com.brewery.application.repository.StoreRepository;
import com.brewery.application.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Store postStore(Store store) {
        Address address = new Address();
        modelMapper.map(store.getLocation(),address);
        address = addressRepository.save(address);
        store.setLocation(address);
        Store store1=storeRepository.save(store);
        return store1;
    }

    @Override
    public Store getStoreById(UUID id) {
        return storeRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Store with id not found"));
    }

    @Override
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public Store updateStore(Store store) {
        Store store1=storeRepository.findById(store.getId()).orElseThrow(()->new ElementNotFoundException("Store with id not found"));
        modelMapper.map(store1,store);
        return storeRepository.save(store1);
    }

    @Override
    public Store patchStore(Store store) {
        Store store1=storeRepository.findById(store.getId()).orElseThrow(()->new ElementNotFoundException("Store with id not found"));
        store1.setName(store.getName());
        storeRepository.save(store1);
        return store1;
    }

    @Override
    public void deleteStoreById(UUID id) {
        storeRepository.deleteById(id);
    }

    @Override
    public void deleteStore() {
        storeRepository.deleteAll();
    }

}
