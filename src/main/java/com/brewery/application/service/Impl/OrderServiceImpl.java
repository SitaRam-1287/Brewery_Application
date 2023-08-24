package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.Invoice;
import com.brewery.application.entity.Item;
import com.brewery.application.entity.Order;
import com.brewery.application.entity.OrderItem;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.repository.InvoiceRepository;
import com.brewery.application.repository.OrderRepository;
import com.brewery.application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ModelMapper modelMapper;

    @Override
    public OrderOutDto createOrder(OrderInDto input) {
        Order order = convertDtoToEntity(input);
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
