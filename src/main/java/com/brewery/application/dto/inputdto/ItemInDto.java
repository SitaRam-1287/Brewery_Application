package com.brewery.application.dto.inputdto;

import com.brewery.application.enums.FoodType;

import lombok.Data;

@Data
public class ItemInDto {

    private String name;

    private String details;

    private Double price;

    private Boolean inStock;

    private FoodType foodType;
}
