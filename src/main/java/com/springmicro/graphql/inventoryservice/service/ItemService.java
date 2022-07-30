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
        return itemMapper.toItemDTO(
                itemRepository.save(
                        itemMapper.toItem(itemDTO)
                )
        );
    }

    public void updateItem(ItemDTO itemDTO, Long id) {
        Item item = fetchItemById(id);
        item.setCategory(new ItemCategory(itemDTO.category().id(), itemDTO.category().name()));
        item.setName(itemDTO.name());
        item.setPrice(itemDTO.price());
        itemRepository.save(item);
    }

    private Item fetchItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }
}
