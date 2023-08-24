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

<<<<<<< HEAD
    @OneToMany
    private List<OrderItem> foodItems;
=======
    @ManyToMany
    private List<Item> foodItems;
>>>>>>> 8fe2e7b345237b28d8411cc86ce6c4664291adcb
}
