package com.brewery.application.dto.inputdto;

import com.brewery.application.entity.Address;
import com.brewery.application.entity.Invoice;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.User;
import com.brewery.application.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderInDto {

    private UUID userId;

    private Address address;

    private List<UUID> items;
}
