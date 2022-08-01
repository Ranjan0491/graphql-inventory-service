package com.springmicro.graphql.inventoryservice.repository;

import com.springmicro.graphql.inventoryservice.model.Item;
import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT item FROM Item item WHERE category = :category")
    List<Item> getItemsByCategory(@Param("category") ItemCategory category);

    @Query(value = "SELECT MAX(id) FROM Item item")
    Long findMaxId();
}
