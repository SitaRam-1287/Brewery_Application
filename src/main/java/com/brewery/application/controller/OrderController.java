package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.*;
import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public InvoiceOutDto createOrder(@RequestHeader UUID userId,@RequestBody OrderInDto input){
        return orderService.createOrder(userId,input);
    }

    @GetMapping("{id}")
    public OrderOutDto getOrder(@PathVariable UUID id){

        return orderService.getOrder(id);
    }

    @PutMapping
    public OrderOutDto updateOrder(OrderInDto input){

        return orderService.updateOrder(input);
    }

    @GetMapping("/dailyReport")
    public DashboardOutputDto getDailyReport(){
        return orderService.getDailyReport();
    }


    @PatchMapping
    public OrderOutDto partialUpdateOrder(OrderInDto input){

        return orderService.partialUpdateOrder(input);
    }

    @GetMapping("/getAll")
    public List<OrderOutDto> getAllOrders(){
        orderService.getDailyReport();
        return orderService.getAllOrders();
    }

    @DeleteMapping("{id}")
    public OrderOutDto deleteOrder(@PathVariable UUID id){

        return orderService.deleteOrder(id);
    }

    @GetMapping("/user")
    public List<OrderItemOutDto> getOrderByUser(@RequestHeader UUID userId){

        return orderService.getOrderByUser(userId);

    }
    @GetMapping("/status")
    public List<OrderOutDto> getOrderByStatus(@RequestParam OrderStatus orderStatus){

        return orderService.getOrderByStatus(orderStatus);
    }

    @GetMapping("/getStatus/{uuid}")
    public OrderStatus getStatus(@PathVariable UUID uuid){

        return orderService.getStatus(uuid);
    }

    @GetMapping("track/{id}")
    public OrderTrackingDto tracKOrder(@PathVariable UUID id){
        return orderService.trackOrder(id);
    }

    @GetMapping("/payment/{id}")
    public InvoiceOutDto initiatePayment(Order order){

        return orderService.initiatePayment(order);

    }

    @GetMapping("/invoice/{orderId}")
    public InvoiceOutDto getInvoice(@PathVariable UUID orderId){
        return orderService.getInvoice(orderId);
    }

    @GetMapping("/{itemId}")
    public List<Order> getOrderByUserIdAndFoodItemsItemId(@RequestHeader UUID userId,@PathVariable UUID itemId){
        return orderService.getOrderByUserIdAndFoodItemsItemId(userId, itemId);
    }

    @PostMapping("/{orderId}")
    public OrderOutDto updateStatus(@PathVariable UUID orderId,@RequestParam OrderStatus orderStatus){
        return orderService.updateStatus(orderId, orderStatus);
    }

}
