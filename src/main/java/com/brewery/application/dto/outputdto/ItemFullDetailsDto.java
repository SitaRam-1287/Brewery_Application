package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.FoodType;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ItemFullDetailsDto {

    private UUID id;

    private String name;

    private String details;

    private String image;

    private Double price;

    private Double rating;

    private FoodType foodType;

    private List<RatingOutDto> ratings;
}
