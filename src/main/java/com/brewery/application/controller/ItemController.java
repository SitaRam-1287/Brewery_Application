package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    private ItemOutDto createItem(@RequestBody ItemInDto item) {
        return itemService.createItem(item);
    }

    @PostMapping("/postImage/{id}")
    private ItemOutDto postImage(@RequestBody MultipartFile image,@PathVariable UUID id){
        return itemService.postImage(image,id);
    }

    @GetMapping("{id}")
    private ItemOutDto getItem(@PathVariable UUID id) {
        return itemService.getItem(id);
    }

    @GetMapping
    private Collection<ItemOutDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PutMapping
    private ItemOutDto updateItem(@RequestBody ItemInDto item) {
        return itemService.updateItem(item);
    }

    @PatchMapping
    private ItemOutDto patchItem(@RequestBody ItemInDto item) {
        return itemService.patchItem(item);
    }

    @DeleteMapping("{id}")
    private void deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
    }

    @DeleteMapping
    private void deleteAllItems() {
        itemService.deleteAllItems();
    }


}
