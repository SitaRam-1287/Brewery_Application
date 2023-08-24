package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.enums.FoodType;
import com.brewery.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/postImage/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    private String postImage(@RequestBody MultipartFile image,@PathVariable UUID id){
        return itemService.postImage(image,id);
    }

    @GetMapping(value = "/getImage/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    private byte[] getImage(@PathVariable UUID id){
        return itemService.getImage(id);
    }

    @GetMapping("{id}")
    private ItemOutDto getItem(@PathVariable UUID id) {
        return itemService.getItem(id);
    }

    @GetMapping("/foodType")
    private Collection<ItemOutDto> getItemByCategory(@RequestParam FoodType foodType){
        return itemService.getItemByCategory(foodType);
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
