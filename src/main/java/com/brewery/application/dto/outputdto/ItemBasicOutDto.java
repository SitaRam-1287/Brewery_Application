package com.brewery.application.dto.outputdto;

import com.brewery.application.enums.FoodType;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemBasicOutDto {

    private UUID id;

    private String name;

    private String image;

    private Float price;

    private String details;
    

}
