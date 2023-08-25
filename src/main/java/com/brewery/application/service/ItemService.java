package com.brewery.application.service;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemBasicOutDto;
import com.brewery.application.dto.outputdto.ItemFullDetailsDto;
import com.brewery.application.enums.FoodType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemService {

    public ItemBasicOutDto createItem(ItemInDto item);

    public ItemFullDetailsDto getItem(UUID id);

    public List<ItemBasicOutDto> getAllItems();

    public ItemBasicOutDto updateItem(ItemInDto item);

    public ItemBasicOutDto patchItem(ItemInDto item);

    public void deleteItem(UUID id);

    public void deleteAllItems();

    public String postImage(MultipartFile image, UUID id);

    public ItemBasicOutDto itemOrderedMore();

    public ItemBasicOutDto itemWithMoreRating();

    public byte[] getImage(@PathVariable UUID id);

    public Collection<ItemBasicOutDto> getItemByCategory(FoodType foodType);

    public List<ItemBasicOutDto> getByItemRating();

    public List<ItemBasicOutDto> getByOrderQuantity();

    public ItemBasicOutDto getByName(String name);
}
