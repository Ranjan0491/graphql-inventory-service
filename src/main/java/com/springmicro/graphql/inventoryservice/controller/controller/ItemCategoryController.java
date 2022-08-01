package com.springmicro.graphql.inventoryservice.controller.controller;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemCategoryDTO;
import com.springmicro.graphql.inventoryservice.service.ItemCategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public record ItemCategoryController (
        ItemCategoryService itemCategoryService
) {

    @QueryMapping
    public List<ItemCategoryDTO> getAllItemCategories() {
        return itemCategoryService.getItemCategories();
    }

    @QueryMapping
    public ItemCategoryDTO getItemCategoryById(@Argument Long id) {
        return itemCategoryService.getItemCategoryById(id);
    }

    @MutationMapping
    public ItemCategoryDTO saveItemCategory(@Argument(name = "category") @Valid ItemCategoryDTO itemCategoryDTO) {
        return itemCategoryService.saveItemCategory(itemCategoryDTO);
    }

    @MutationMapping
    public ItemCategoryDTO updateItemCategory(@Argument(name = "category") @Valid ItemCategoryDTO itemCategoryDTO, @Argument(name = "id") Long id) {
        System.out.println(itemCategoryDTO);
        return itemCategoryService.updateItemCategory(itemCategoryDTO, id);
    }
}
