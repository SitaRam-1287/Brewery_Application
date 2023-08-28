package com.brewery.application.service;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderItemOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService{

    public InvoiceOutDto initiatePayment(Order order);

    public InvoiceOutDto createOrder(OrderInDto input);

    public OrderOutDto getOrder(UUID id);


    public OrderOutDto updateOrder(OrderInDto input);

    public OrderOutDto partialUpdateOrder(OrderInDto input);

    public List<OrderOutDto> getAllOrders();

    public OrderOutDto deleteOrder(UUID id);

    public List<OrderItemOutDto> getOrderByUser(UUID id);

    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus);

    public List<Order> getOrderByUserIdAndFoodItemsItemId(UUID userId,UUID itemId);

    public OrderOutDto updateStatus(UUID orderId,OrderStatus orderStatus);

}
