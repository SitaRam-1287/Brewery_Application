package com.brewery.application.entity;

import com.brewery.application.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Address address;

    @OneToOne
    private Invoice invoice;

    @OneToMany
    private List<OrderItem> foodItems;

    private String userLatitude;

    private String userLongitude;

    @ManyToOne
    private Store store;

    private Double totalAmount;
}
