package com.brewery.application.service;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.*;
import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface OrderService{

    public InvoiceOutDto initiatePayment(Order order);
    public InvoiceOutDto createOrder(UUID userId,OrderInDto input);

    public OrderOutDto getOrder(UUID id);
    public OrderTrackingDto trackOrder(UUID id);
    public InvoiceOutDto getInvoice(UUID orderId);
    public OrderStatus getStatus(UUID uuid);
    public OrderOutDto updateOrder(OrderInDto input);

    public OrderOutDto partialUpdateOrder(OrderInDto input);

    public List<OrderOutDto> getAllOrders();

    public OrderOutDto deleteOrder(UUID id);

    public List<OrderItemOutDto> getOrderByUser(UUID id);
    public HashMap<LocalDate,List<Double>> getDailyReport();

    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus);

    public List<Order> getOrderByUserIdAndFoodItemsItemId(UUID userId,UUID itemId);

    public OrderOutDto updateStatus(UUID orderId,OrderStatus orderStatus);

}
