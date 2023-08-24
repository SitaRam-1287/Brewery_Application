package com.brewery.application.service;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.enums.FoodType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ItemService {

    public ItemOutDto createItem(ItemInDto item);

    public ItemOutDto getItem(UUID id);

    public List<ItemOutDto> getAllItems();

    public ItemOutDto updateItem(ItemInDto item);

    public ItemOutDto patchItem(ItemInDto item);

    public void deleteItem(UUID id);

    public void deleteAllItems();

    public String postImage(MultipartFile image, UUID id);

    public byte[] getImage(@PathVariable UUID id);

    public Collection<ItemOutDto> getItemByCategory(FoodType foodType);
}
