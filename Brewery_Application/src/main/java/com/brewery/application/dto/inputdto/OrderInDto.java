package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderInDto {

    private UUID userId;

    private UUID addressId;

    private UUID storeId;

    private List<OrderItemInDto> items;
}
