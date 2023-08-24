package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.entity.Item;
import com.brewery.application.enums.FoodType;
import com.brewery.application.repository.ItemRepository;
import com.brewery.application.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ItemOutDto createItem(ItemInDto item) {
        Item item1=modelMapper.map(item,Item.class);
        Item saveItem=itemRepository.save(item1);
        return modelMapper.map(saveItem,ItemOutDto.class);
    }

    @Override
    public ItemOutDto getItem(UUID id) {
        Item item=itemRepository.findById(id).orElseThrow(()->new RuntimeException("Item with id not found"));
        return modelMapper.map(item,ItemOutDto.class);
    }
    public String postImage(MultipartFile image, UUID id){
        String s;
        try{
            byte[] arr;
            arr = image.getBytes();
            s = Base64.getEncoder().encodeToString(arr);
            Item item = itemRepository.findById(id).orElseThrow(()->new RuntimeException("Item with given id is not found"));
            item.setImage(s);
            item = itemRepository.save(item);
            String image1 = item.getImage();
            return image1;

        }
        catch(Exception e){
            throw new RuntimeException("Item with given id is not found");
        }
    }

    @Override
    public List<ItemOutDto> getItemByCategory(FoodType foodType) {
        List<Item> items=itemRepository.findByFoodType(foodType);
        return items.stream().map(item ->modelMapper.map(item, ItemOutDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ItemOutDto> getAllItems() {
        List<Item> items=itemRepository.findAll();
        return items.stream().map(item ->modelMapper.map(item, ItemOutDto.class)).collect(Collectors.toList());

    }

    @Override
    public ItemOutDto updateItem(ItemInDto item) {
        Item item1=modelMapper.map(item,Item.class);
        Item existingItem=itemRepository.findById(item1.getId()).orElseThrow(()->new RuntimeException("Item with id not found"));
        modelMapper.map(item1,existingItem);
        Item saveItem=itemRepository.save(existingItem);
        return modelMapper.map(saveItem,ItemOutDto.class);
    }

    @Override
    public ItemOutDto patchItem(ItemInDto item) {
        return null;
    }

    @Override
    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }



}