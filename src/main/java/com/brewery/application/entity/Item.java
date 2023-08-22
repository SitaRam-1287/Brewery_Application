package com.brewery.application.entity;

import com.brewery.application.enums.FoodType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private UUID id;

    private String name;

    private String details;

    private Byte[] image;

    private Boolean inStock;

    private FoodType foodType;
}
