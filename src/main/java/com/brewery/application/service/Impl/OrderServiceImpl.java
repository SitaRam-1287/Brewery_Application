package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.inputdto.OrderItemInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.Invoice;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.Order;
import com.brewery.application.entity.OrderItem;
import com.brewery.application.entity.User;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.repository.*;
import com.brewery.application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderOutDto createOrder(OrderInDto input,LocalDateTime orderedTime) {
        Order order = convertDtoToEntity(input);
        User user = userRepository.findById(input.getUserId()).orElseThrow(()->new RuntimeException());
        List<OrderItemInDto> foodItems = input.getItems();
        List<OrderItem> foodItems1 = new ArrayList<>();
        for(OrderItemInDto item : foodItems){
           OrderItem it = new OrderItem();
           Item item1 = itemRepository.findById(item.getItemId()).orElseThrow(()->new RuntimeException());
           it.setItem(item1);
           it.setQuantity(item.getQuantity());
           it = orderItemRepository.save(it);
           foodItems1.add(it);

        }
        order.setFoodItems(foodItems1);
        order.setUser(user);
        order.setOrderedTime(orderedTime);
        order = orderRepository.save(order);
        return convertEntityToDto(order);
    }

    @Override
    public OrderOutDto getOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order with given id is not found"));
        return convertEntityToDto(order);
    }

    public InvoiceOutDto initiatePayment(UUID orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order with given id is not found"));
        Double Amount = 0.0;
        Double TotalAmount = 0.0;
        List<OrderItem> foodItems = order.getFoodItems();
        for(OrderItem foodItem : foodItems){
            Item item = foodItem.getItem();
            Amount+=item.getPrice();
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(Amount);
        invoice.setGst(Amount*0.1);
        invoice.setDeliveryFee(Amount*0.05);
        invoice.setTotalAmount(Amount*0.1+Amount*0.05);
        invoiceRepository.save(invoice);
        order.setInvoice(invoice);
        orderRepository.save(order);
        return modelMapper.map(invoice,InvoiceOutDto.class);
    }

    @Override
    public OrderOutDto updateOrder(OrderInDto input) {
        Order order = convertDtoToEntity(input);
        Order existingOrder = orderRepository.findById(order.getId()).orElseThrow(()->new RuntimeException("Order with given id is not found"));
        modelMapper.map(order,existingOrder);
        Order currentOrder = orderRepository.save(existingOrder);
        return convertEntityToDto(currentOrder);
    }

    @Override
    public OrderOutDto partialUpdateOrder(OrderInDto input) {
        return null;
    }

    @Override
    public List<OrderOutDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderOutDto> orderList = orders.stream().map(order -> convertEntityToDto(order)).collect(Collectors.toList());
        return orderList;
    }

    @Override
    public OrderOutDto deleteOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with given Id"));
        orderRepository.delete(order);
        return convertEntityToDto(order);
    }

    @Override
    public List<OrderOutDto> getOrderByUser(UUID id) {
        List<Order> orders = orderRepository.findOrderByUserId(id);
        return orders.stream().map(order->convertEntityToDto(order)).collect(Collectors.toList());
    }

    @Override
    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);
        return orders.stream().map(order->convertEntityToDto(order)).collect(Collectors.toList());
    }

    public Order convertDtoToEntity(OrderInDto input){
        return modelMapper.map(input,Order.class);
    }

    public OrderOutDto convertEntityToDto(Order order){
        return modelMapper.map(order,OrderOutDto.class);
    }
}
