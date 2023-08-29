package com.brewery.application.controller;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderItemOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
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
    public InvoiceOutDto createOrder(@RequestBody OrderInDto input){
        return orderService.createOrder(input);
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
    public AbstractMap<LocalDate,List<Double>> getDailyReport(){
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

    @GetMapping("/payment/{id}")
    public InvoiceOutDto initiatePayment(Order order){

        return orderService.initiatePayment(order);

    }

    @GetMapping("/{userId}/{itemId}")
    public List<Order> getOrderByUserIdAndFoodItemsItemId(@PathVariable UUID userId,@PathVariable UUID itemId){
        return orderService.getOrderByUserIdAndFoodItemsItemId(userId, itemId);
    }

    @PostMapping("/{orderId}")
    public OrderOutDto updateStatus(@PathVariable UUID orderId,@RequestParam OrderStatus orderStatus){
        return orderService.updateStatus(orderId, orderStatus);
    }

}
