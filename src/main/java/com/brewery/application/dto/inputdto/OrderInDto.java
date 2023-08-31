package com.brewery.application.dto.inputdto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderInDto {

    private UUID userId;

    private String userLatitude;

    private String userLongitude;

    private UUID storeId;

    private List<OrderItemInDto> items;
}
