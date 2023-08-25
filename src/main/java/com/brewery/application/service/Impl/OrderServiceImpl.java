package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.inputdto.OrderItemInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.*;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.repository.*;
import com.brewery.application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private AddressRepository addressRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InvoiceOutDto createOrder(OrderInDto input) {
        Order order = new Order();
        User user = userRepository.findById(input.getUserId()).orElseThrow(()->new RuntimeException("Item with given id is not found"));
        List<OrderItemInDto> foodItems = input.getItems();
        List<OrderItem> foodItems1 = new ArrayList<>();
        for(OrderItemInDto item : foodItems){
           OrderItem it = new OrderItem();
           Item item1 = itemRepository.findById(item.getItemId()).orElseThrow(()->new RuntimeException("Item with given id is not found"));
           it.setItem(item1);
           it.setQuantity(item.getQuantity());
           item1.setQuantityOrdered(item.getQuantity()+ item1.getQuantityOrdered());
           it = orderItemRepository.save(it);
           foodItems1.add(it);

        }
        Address address = addressRepository.findById(input.getAddressId()).orElseThrow(()->new RuntimeException());
        AddressOutDto addressOutDto = modelMapper.map(address, AddressOutDto.class);
        order.setFoodItems(foodItems1);
        order.setUser(user);
        order.setOrderedTime(LocalDateTime.now());
        order.setAddress(address);
        order = orderRepository.save(order);
        return initiatePayment(order);
    }

    @Override
    public OrderOutDto getOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order with given id is not found"));
        return convertEntityToDto(order);
    }

    public InvoiceOutDto initiatePayment(Order order){
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
        invoice.setDeliveryFee(40.0);
        invoice.setTotalAmount(Amount*0.05+40.0);
        invoice = invoiceRepository.save(invoice);
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
        List<OrderOutDto> orderList = orders.stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
        return orders.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);
        return orders.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public Order convertDtoToEntity(OrderInDto input){
        return modelMapper.map(input,Order.class);
    }

    public OrderOutDto convertEntityToDto(Order order){
        return modelMapper.map(order,OrderOutDto.class);
    }
}
