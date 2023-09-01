package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.Address;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.OrderItem;
import com.brewery.application.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderOutDto {

    private UUID id;

    private LocalDateTime orderedTime;

    private UUID userId;

    private UUID storeId;

    private String storeName;

    private String userFirstName;

    private String userLastName;

    private String userLatitude;

    private String userLongitude;

    private AddressOutDto  address;

    private List<OrderItem> foodItems;

    private OrderStatus status;
}
