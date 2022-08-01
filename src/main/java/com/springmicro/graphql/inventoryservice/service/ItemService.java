package com.springmicro.graphql.inventoryservice.service;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemDTO;
import com.springmicro.graphql.inventoryservice.exceptions.EntityNotFoundException;
import com.springmicro.graphql.inventoryservice.mapper.ItemMapper;
import com.springmicro.graphql.inventoryservice.model.Item;
import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import com.springmicro.graphql.inventoryservice.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ItemService(
        ItemRepository itemRepository,
        ItemCategoryService itemCategoryService,
        ItemMapper itemMapper
) {

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toItemDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById(Long id) {
        return itemMapper.toItemDTO(fetchItemById(id));
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        var itemCategory = itemCategoryService.findItemCategoryByName(itemDTO.category().name());
        var item = itemMapper.toItem(itemDTO);
        item.setId(itemRepository.findMaxId() + 1);
        item.setCategory(itemCategory);
        return itemMapper.toItemDTO(
                itemRepository.save(item)
        );
    }

    public ItemDTO updateItem(ItemDTO itemDTO, Long id) {
        var itemCategory = itemCategoryService.findItemCategoryByName(itemDTO.category().name());
        var item = fetchItemById(id);
        item.setCategory(itemCategory);
        item.setName(itemDTO.name());
        item.setPrice(itemDTO.price());
        return itemMapper.toItemDTO(itemRepository.save(item));
    }

    public List<ItemDTO> getAllItemsByCategoryId(Long categoryId) {
        return itemRepository.getItemsByCategory(new ItemCategory(categoryId, null))
                .stream().map(itemMapper::toItemDTO)
                .collect(Collectors.toList());
    }

    private Item fetchItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    public List<ItemDTO> getAllItemsByCategoryName(String categoryName) {
        var itemCategory = itemCategoryService.findItemCategoryByName(categoryName);
        return itemRepository.getItemsByCategory(itemCategory)
                .stream().map(itemMapper::toItemDTO)
                .collect(Collectors.toList());
    }
}
