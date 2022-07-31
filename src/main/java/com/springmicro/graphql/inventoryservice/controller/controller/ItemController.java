package com.springmicro.graphql.inventoryservice.controller.controller;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemDTO;
import com.springmicro.graphql.inventoryservice.service.ItemService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@SchemaMapping(value = "/api/graphql/v1/items")
public record ItemController (
        ItemService itemService
) {

    @QueryMapping
    public List<ItemDTO> getItems() {
        return itemService.getAllItems();
    }

    @QueryMapping
    public ItemDTO getItemById(@Argument Long id) {
        return itemService.getItemById(id);
    }
}
