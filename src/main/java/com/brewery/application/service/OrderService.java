package com.brewery.application.service;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService{

    public OrderOutDto createOrder(OrderInDto input);

    public OrderOutDto getOrder(UUID id);

    public OrderOutDto updateOrder(OrderInDto input);

    public OrderOutDto partialUpdateOrder(OrderInDto input);

    public List<OrderOutDto> getAllOrders();

    public OrderOutDto deleteOrder(UUID id);

    public List<OrderOutDto> getOrderByUser(UUID id);
    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus);

}