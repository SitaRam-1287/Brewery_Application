package com.brewery.application.dto.inputdto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemInDto {

    private UUID itemId;

    private Integer quantity;
}
