package com.brewery.application.entity;

import com.brewery.application.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    private UUID orderId;

    private LocalDateTime orderDateTime;

    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="User",referencedColumnName = "id")
    private User user;

    private Address orderAddress;
}
