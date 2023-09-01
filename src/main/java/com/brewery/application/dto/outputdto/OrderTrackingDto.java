package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.*;
import com.brewery.application.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderTrackingDto {

    private LocalDateTime orderedTime;

    private Invoice invoice;

    private List<OrderItem> foodItems;

    private String userLatitude;

    private String userLongitude;

    private String storeLocationLatitude;

    private String storeLocationLongitude;

    private Double totalAmount;
}
