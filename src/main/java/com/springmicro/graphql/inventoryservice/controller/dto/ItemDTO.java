package com.springmicro.graphql.inventoryservice.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record ItemDTO (
     Long id,
     @NotNull ItemCategoryDTO category,
     @NotNull String name,
     @NotNull Integer quantity,
     @NotNull @Positive Double price
) {}
