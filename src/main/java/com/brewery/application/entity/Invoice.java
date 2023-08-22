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
    private UUID invoiceId;

    private Double amount;

    private Double deliveryFee;

    private Double gst;

    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "orderId")
    private Order order;
}
