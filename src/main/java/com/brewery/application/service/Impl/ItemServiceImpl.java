package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.ItemInDto;
import com.brewery.application.dto.outputdto.ItemOutDto;
import com.brewery.application.fascade.ItemFascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class ItemServiceImpl {
    @Autowired
    private ItemFascade itemFascade;

    public ItemOutDto createItem(ItemInDto item) {
        return itemFascade.createItem(item);
    }

    public ItemOutDto getItem(UUID id) {
        return itemFascade.getItem(id);
    }

    public Collection<ItemOutDto> getAllItems() {
        return itemFascade.getAllItems();
    }

    public ItemOutDto updateItem(ItemInDto item) {
        return itemFascade.updateItem(item);
    }

    public ItemOutDto patchItem(ItemInDto item) {
        return itemFascade.patchItem(item);
    }

    public void deleteItem(UUID id) {
        itemFascade.deleteItem(id);
    }

    public void deleteAllItems() {
        itemFascade.deleteAllItems();
    }
}
