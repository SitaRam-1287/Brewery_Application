package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.Item;
import com.brewery.application.entity.Order;
import com.brewery.application.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class RatingInDto {

    private String comment;

    private Integer rating;

    private UUID itemId;

    private UUID orderId;

    private UUID userId;
}
