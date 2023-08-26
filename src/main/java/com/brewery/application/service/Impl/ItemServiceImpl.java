package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemBasicOutDto;
import com.brewery.application.dto.outputdto.ItemFullDetailsDto;
import com.brewery.application.dto.outputdto.RatingOutDto;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.Rating;
import com.brewery.application.enums.FoodType;
import com.brewery.application.repository.ItemRepository;
import com.brewery.application.repository.RatingRepository;
import com.brewery.application.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ItemBasicOutDto createItem(ItemInDto item) {
        Item item1=modelMapper.map(item,Item.class);
        Item saveItem=itemRepository.save(item1);
        return modelMapper.map(saveItem, ItemBasicOutDto.class);
    }

    @Override
    public ItemFullDetailsDto getItem(UUID id) {

        Item item=itemRepository.findById(id).orElseThrow(()->new RuntimeException("Item with id not found"));

        List<Rating> ratings = ratingRepository.findRatingByItemId(item.getId());

        List<RatingOutDto> ratingDto = ratings.stream().map(rating -> modelMapper.map(rating, RatingOutDto.class)).collect(Collectors.toList());

        ItemFullDetailsDto itemDto = modelMapper.map(item, ItemFullDetailsDto.class);

        itemDto.setRatings(ratingDto);

        return itemDto;
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
    public List<ItemBasicOutDto> getItemByCategory(FoodType foodType) {
        List<Item> items=itemRepository.findByFoodType(foodType);
        return items.stream().map(item ->modelMapper.map(item, ItemBasicOutDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ItemBasicOutDto> getByItemRating() {
        List<Item> items = itemRepository.findByItemRatingAndFoodType(FoodType.CRAFT);
        return items.stream().map(item-> modelMapper.map(item, ItemBasicOutDto.class)).collect(Collectors.toList());
    }


    @Override
    public List<ItemBasicOutDto> getByOrderQuantity() {
        List<Item> items = itemRepository.findByItemOrderedAndFoodType(FoodType.CRAFT);
        return items.stream().map(item-> modelMapper.map(item, ItemBasicOutDto.class)).collect(Collectors.toList());
    }

    @Override
    public ItemBasicOutDto getByName(String name) {
        Item item=itemRepository.findByName(name);
        return modelMapper.map(item, ItemBasicOutDto.class);
    }

    @Override
    public List<ItemBasicOutDto> getAllItems() {
        List<Item> items=itemRepository.findAll();
        return items.stream().map(item ->modelMapper.map(item, ItemBasicOutDto.class)).collect(Collectors.toList());

    }
    public byte[] getImage(@PathVariable UUID id){
        String s;
        try{
            Item item = itemRepository.findById(id).orElseThrow(()->new RuntimeException("Item with given id is not found"));
            byte arr[];
            s = item.getImage();
            arr = Base64.getDecoder().decode(s);
            return arr;
        }
        catch(Exception e){
            throw new RuntimeException("Item with given id is not found");
        }
    }

    @Override
    public ItemBasicOutDto updateItem(ItemInDto item) {
        Item item1=modelMapper.map(item,Item.class);
        Item existingItem=itemRepository.findById(item1.getId()).orElseThrow(()->new RuntimeException("Item with id not found"));
        modelMapper.map(item1,existingItem);
        Item saveItem=itemRepository.save(existingItem);
        return modelMapper.map(saveItem, ItemBasicOutDto.class);
    }

    @Override
    public ItemBasicOutDto patchItem(ItemInDto item) {
        return null;
    }

    @Override
    public ItemBasicOutDto itemOrderedMore(){
        return modelMapper.map(itemRepository.findByItemCount(), ItemBasicOutDto.class);
    }

    @Override
    public ItemBasicOutDto itemWithMoreRating() {
        return modelMapper.map(itemRepository.findByMoreRating(), ItemBasicOutDto.class);
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