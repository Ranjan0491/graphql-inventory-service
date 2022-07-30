package com.springmicro.graphql.inventoryservice.mapper;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemCategoryDTO;
import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import org.springframework.stereotype.Component;

@Component
public record ItemCategoryMapper() {

    public ItemCategory toItemCategory(ItemCategoryDTO itemCategoryDTO) {
        if(itemCategoryDTO == null)
            return null;
        return new ItemCategory(itemCategoryDTO.id(), itemCategoryDTO.name());
    }

    public ItemCategoryDTO toItemCategoryDTO(ItemCategory itemCategory) {
        if(itemCategory == null)
            return null;
        return new ItemCategoryDTO(itemCategory.getId(), itemCategory.getName());
    }

}
