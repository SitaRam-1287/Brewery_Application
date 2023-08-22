package com.brewery.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double amount;

    private Double deliveryFee;

    private Double gst;

<<<<<<< HEAD
    @OneToOne
    @JoinColumn(name="order_id",referencedColumnName = "orderId")
    private Order order;
=======
    private Double totalAmount;
>>>>>>> e728991e50111404c743ed9ef95ae248f447ac7f
}
