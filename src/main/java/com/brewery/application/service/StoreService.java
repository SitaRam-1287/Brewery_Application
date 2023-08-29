package com.brewery.application.service;

import com.brewery.application.entity.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    public Store postStore(Store store);

    public Store getStoreById(UUID id);

    public List<Store> getAllStore();

    public Store updateStore(Store store);

    public Store patchStore(Store store);

    public void deleteStoreById(UUID id);

    public void deleteStore();
}
