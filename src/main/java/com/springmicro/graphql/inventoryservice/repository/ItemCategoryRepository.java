package com.springmicro.graphql.inventoryservice.repository;

import com.springmicro.graphql.inventoryservice.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
}
