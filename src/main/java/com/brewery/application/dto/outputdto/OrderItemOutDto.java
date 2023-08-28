package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderItemOutDto {

    private UUID id;

    private LocalDateTime orderedTime;

    private UUID userId;

    private UUID storeId;

    private List<OrderItem> foodItems;
}
