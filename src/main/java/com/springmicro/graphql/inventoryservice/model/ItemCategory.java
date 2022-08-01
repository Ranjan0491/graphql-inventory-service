package com.springmicro.graphql.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_category")
public class ItemCategory {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
}
