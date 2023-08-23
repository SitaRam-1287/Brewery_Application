package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.FoodType;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ItemOutDto {

    private String name;

    private String details;
    @Lob
    private String image;

    private Double price;

    private Boolean inStock;

    private FoodType foodType;
}
