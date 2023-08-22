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
@Table(name="rating")
public class Rating {
    @Id
    private UUID ratingId;

    private String comment;

    private int rating;

    @ManyToOne
    @JoinColumn(name="item_id",referencedColumnName = "id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id",referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

}
