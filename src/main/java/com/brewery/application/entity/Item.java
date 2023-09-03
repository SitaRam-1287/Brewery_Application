package com.brewery.application.entity;

import com.brewery.application.enums.FoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(length = 1000)
    private String details;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;

    private Double price;

    private Boolean inStock;

    private Integer quantityOrdered;

    private Double rating;

    private FoodType foodType;
}
