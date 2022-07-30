package com.springmicro.graphql.inventoryservice.mapper;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemDTO;
import com.springmicro.graphql.inventoryservice.model.Item;
import org.springframework.stereotype.Component;

@Component
public record ItemMapper(ItemCategoryMapper itemCategoryMapper) {

    public Item toItem(ItemDTO itemDTO) {
        if(itemDTO == null)
            return null;
        return new Item(itemDTO.id(), itemCategoryMapper().toItemCategory(itemDTO.category()), itemDTO.name(), itemDTO.price());
    }

    public ItemDTO toItemDTO(Item item) {
        if(item == null)
            return null;
        return new ItemDTO(item.getId(), itemCategoryMapper().toItemCategoryDTO(item.getCategory()), item.getName(), item.getPrice());
    }
}
