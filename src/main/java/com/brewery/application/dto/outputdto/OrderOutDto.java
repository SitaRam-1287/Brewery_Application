package com.brewery.application.dto.outputdto;

import com.brewery.application.entity.Address;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.OrderItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderOutDto {

    private UUID id;
<<<<<<< HEAD

=======
>>>>>>> 8266bbc618bbaffe807f5e9ed00485e8c0a253b7
    private LocalDateTime orderedTime;

    private UUID userId;

    private String userFirstName;

    private String userLastName;

    private Address orderAddress;

    private List<OrderItem> foodItems;
}
