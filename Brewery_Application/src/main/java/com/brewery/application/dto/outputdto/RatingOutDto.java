package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.Item;
import com.brewery.application.entity.Order;
import com.brewery.application.entity.User;
import com.brewery.application.enums.FoodType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class RatingOutDto {

    private UUID id;
    private String comment;

    private Integer rating;

    private String itemName;

    private FoodType itemFoodType;

    private String userFirstName;

    private String userLastName;

}
