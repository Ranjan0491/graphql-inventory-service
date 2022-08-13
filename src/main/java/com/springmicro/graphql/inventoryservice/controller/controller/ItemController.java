package com.springmicro.graphql.inventoryservice.controller.controller;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemDTO;
import com.springmicro.graphql.inventoryservice.service.ItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public record ItemController (
        ItemService itemService) {

    @QueryMapping
    public List<ItemDTO> getItems() {
        return itemService.getAllItems();
    }

    @QueryMapping
    public ItemDTO getItemById(@Argument Long id) {
        return itemService.getItemById(id);
    }

    @QueryMapping
    public List<ItemDTO> getItemsByCategoryId(@Argument Long categoryId) {
        return itemService.getAllItemsByCategoryId(categoryId);
    }

    @QueryMapping
    public List<ItemDTO> getItemsByCategoryName(@Argument String categoryName) {
        return itemService.getAllItemsByCategoryName(categoryName);
    }

    @MutationMapping
    public ItemDTO saveItem(@Argument(name = "item") @Valid ItemDTO itemDTO) {
        return itemService.saveItem(itemDTO);
    }

    @MutationMapping
    public ItemDTO updateItem(@Argument(name = "item") @Valid ItemDTO itemDTO, @Argument(name = "id") Long id) {
        return itemService.updateItem(itemDTO, id);
    }
}
