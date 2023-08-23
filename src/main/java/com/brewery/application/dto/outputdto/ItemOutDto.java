package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.FoodType;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemOutDto {

    private UUID id;
    private String name;

    private String details;

    private byte[] image;

    private Double price;

    private Boolean inStock;

    private FoodType foodType;
}
