package com.springmicro.graphql.inventoryservice.service;

import com.springmicro.graphql.inventoryservice.controller.dto.ItemCategoryDTO;
import com.springmicro.graphql.inventoryservice.exceptions.EntityNotFoundException;
import com.springmicro.graphql.inventoryservice.mapper.ItemCategoryMapper;
import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import com.springmicro.graphql.inventoryservice.repository.ItemCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record ItemCategoryService(
        ItemCategoryRepository itemCategoryRepository,
        ItemCategoryMapper itemCategoryMapper
) {

    public List<ItemCategoryDTO> getItemCategories() {
        return itemCategoryRepository.findAll().stream()
                .map(itemCategoryMapper::toItemCategoryDTO)
                .collect(Collectors.toList());
    }

    public ItemCategoryDTO getItemCategoryById(Long id) {
        return itemCategoryMapper.toItemCategoryDTO(findItemCategoryById(id));
    }

    public ItemCategoryDTO saveItemCategory(ItemCategoryDTO itemCategoryDTO) {
        return itemCategoryMapper.toItemCategoryDTO(
                itemCategoryRepository.save(
                        itemCategoryMapper.toItemCategory(itemCategoryDTO)
                )
        );
    }

    public void updateItemCategory(ItemCategoryDTO itemCategoryDTO, Long id) {
        var itemCategory = findItemCategoryById(id);
        itemCategory.setName(itemCategoryDTO.name());
        itemCategoryRepository.save(itemCategory);
    }

    private ItemCategory findItemCategoryById(Long id) {
        return itemCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item Category not found with id: " + id));
    }
}
