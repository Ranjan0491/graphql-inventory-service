package com.springmicro.graphql.inventoryservice.repository;

import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    Optional<ItemCategory> findByName(String name);

    @Query(value = "SELECT MAX(id) FROM ItemCategory itemCategory")
    Long findMaxId();
}
