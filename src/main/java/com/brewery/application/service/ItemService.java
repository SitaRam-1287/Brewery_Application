package com.brewery.application.service;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;

import java.util.Collection;
import java.util.UUID;

public interface ItemService {

    public ItemOutDto createItem(ItemInDto item);

    public ItemOutDto getItem(UUID id);

    public Collection<ItemOutDto> getAllItems();

    public ItemOutDto updateItem(ItemInDto item);

    public ItemOutDto patchItem(ItemInDto item);

    public void deleteItem(UUID id);

    public void deleteAllItems();
}
