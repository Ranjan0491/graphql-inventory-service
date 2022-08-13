package com.springmicro.graphql.inventoryservice.service;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemCategoryDTO;
import com.springmicro.graphql.inventoryservice.exceptions.EntityNotFoundException;
import com.springmicro.graphql.inventoryservice.mapper.ItemCategoryMapper;
import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import com.springmicro.graphql.inventoryservice.repository.ItemCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ItemCategoryService(
        ItemCategoryRepository itemCategoryRepository,
        ItemCategoryMapper itemCategoryMapper) {

    public List<ItemCategoryDTO> getItemCategories() {
        return itemCategoryRepository.findAll().stream()
                .map(itemCategoryMapper::toItemCategoryDTO)
                .collect(Collectors.toList());
    }

    public ItemCategoryDTO getItemCategoryById(Long id) {
        return itemCategoryMapper.toItemCategoryDTO(findItemCategoryById(id));
    }

    public ItemCategoryDTO saveItemCategory(ItemCategoryDTO itemCategoryDTO) {
        var itemCategory = itemCategoryMapper.toItemCategory(itemCategoryDTO);
        itemCategory.setId(itemCategoryRepository.findMaxId() + 1);
        itemCategory.setName(itemCategory.getName().toUpperCase());
        return itemCategoryMapper.toItemCategoryDTO(
                itemCategoryRepository.save(itemCategory)
        );
    }

    public ItemCategoryDTO updateItemCategory(ItemCategoryDTO itemCategoryDTO, Long id) {
        var itemCategory = findItemCategoryById(id);
        itemCategory.setName(itemCategoryDTO.name().toUpperCase());
        return itemCategoryMapper.toItemCategoryDTO(
                itemCategoryRepository.save(itemCategory)
        );
    }

    private ItemCategory findItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item Category not found with id: " + id));
    }

    public ItemCategory findItemCategoryByName(String name) {
        return itemCategoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Item Category not found with name: " + name));
    }
}
