package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.Address;
import com.brewery.application.entity.Item;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderOutDto {
    private LocalDateTime orderedTime;

    private UUID userId;

    private String userFirstName;

    private String userLastName;

    private Address orderAddress;

    private List<Item> foodItems;
}
