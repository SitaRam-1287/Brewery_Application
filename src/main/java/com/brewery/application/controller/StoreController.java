package com.brewery.application.controller;


import com.brewery.application.entity.Store;
import com.brewery.application.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @PostMapping
    private Store postStore(@RequestBody Store store){
        return storeService.postStore(store);
    }

    @GetMapping("/{id}")
    private Store getStoreById(@PathVariable UUID id){
        return storeService.getStoreById(id);
    }

    @GetMapping
    private List<Store> getAllStores(){
        return storeService.getAllStore();
    }

    @PutMapping
    private Store updateStore(@RequestBody Store store){
        return storeService.updateStore(store);
    }

    @PatchMapping
    private Store patchStore(@RequestBody Store store){
        return storeService.patchStore(store);
    }

    @DeleteMapping("/{id}")
    private void deleteStoreById(@PathVariable UUID id){
        storeService.deleteStoreById(id);
    }

    @DeleteMapping
    private void deleteStore(){
        storeService.deleteStore();
    }
}
