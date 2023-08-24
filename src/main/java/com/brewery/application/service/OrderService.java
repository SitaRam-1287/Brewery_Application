package com.brewery.application.service;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService{

    public InvoiceOutDto initiatePayment(UUID orderId);

    public OrderOutDto createOrder(OrderInDto input, LocalDateTime orderedTime);

    public OrderOutDto getOrder(UUID id);

    public OrderOutDto updateOrder(OrderInDto input);

    public OrderOutDto partialUpdateOrder(OrderInDto input);

    public List<OrderOutDto> getAllOrders();

    public OrderOutDto deleteOrder(UUID id);

    public List<OrderOutDto> getOrderByUser(UUID id);
    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus);

}