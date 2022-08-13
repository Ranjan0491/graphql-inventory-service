package com.springmicro.graphql.inventoryservice.controller.dto;

import javax.validation.constraints.NotNull;

public record ItemCategoryDTO (
        Long id,
        @NotNull String name) {}
