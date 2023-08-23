package com.brewery.application.fascade;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.entity.Item;
import com.brewery.application.repository.ItemRepository;
import com.brewery.application.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Component
public class ItemFascade implements ItemService {
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
        Item getItem=itemRepository.findById(id).orElseThrow(()->new RuntimeException("Item with id not found"));
        return modelMapper.map(getItem,ItemOutDto.class);
    }

    @Override
    public Collection<ItemOutDto> getAllItems() {
        Collection<Item> getAllItems=itemRepository.findAll();
        return Collections.singleton(modelMapper.map(getAllItems, ItemOutDto.class));
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
