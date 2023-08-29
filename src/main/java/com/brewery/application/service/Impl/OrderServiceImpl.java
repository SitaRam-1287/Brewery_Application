package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.inputdto.OrderItemInDto;
import com.brewery.application.dto.outputdto.AddressOutDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.OrderItemOutDto;
import com.brewery.application.dto.outputdto.OrderOutDto;
import com.brewery.application.entity.*;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.*;
import com.brewery.application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
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
    private StoreRepository storeRepository;

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
           if(item1.getQuantityOrdered()!=null){
               item1.setQuantityOrdered(item.getQuantity()+ item1.getQuantityOrdered());
           }
           else{
               item1.setQuantityOrdered(item.getQuantity());
           }

           it = orderItemRepository.save(it);
           foodItems1.add(it);

        }
        Address address = addressRepository.findById(input.getAddressId()).orElseThrow(()->new RuntimeException());
        AddressOutDto addressOutDto = modelMapper.map(address, AddressOutDto.class);
        Store store=storeRepository.findById(input.getStoreId()).orElseThrow(()->new RuntimeException("Store with id not found"));
        order.setStore(store);
        order.setFoodItems(foodItems1);
        order.setUser(user);
        order.setOrderedTime(LocalDateTime.now());
        order.setAddress(address);
        order = orderRepository.save(order);
        return initiatePayment(order);
    }

    @Override
    public OrderOutDto getOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
        return convertEntityToDto(order);
    }

    @Override
    public InvoiceOutDto getInvoice(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
        return modelMapper.map(order.getInvoice(),InvoiceOutDto.class);
    }

    @Override
    public OrderStatus getStatus(UUID uuid) {
        Order order = orderRepository.findById(uuid).orElseThrow(()-> new ElementNotFoundException("Order with given id is not found"));
        return order.getStatus();
    }


    public InvoiceOutDto initiatePayment(Order order){
        Double Amount = 0.0;
        Double TotalAmount = 0.0;
        List<OrderItem> foodItems = order.getFoodItems();
        for(OrderItem foodItem : foodItems){
            Item item = foodItem.getItem();
            Amount+=item.getPrice()*foodItem.getQuantity();
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(Amount);
        invoice.setGst(Amount*0.05);
        invoice.setDeliveryFee(40.0);
        invoice.setTotalAmount(Amount+Amount*0.05+40.0);
        invoice = invoiceRepository.save(invoice);
        order.setInvoice(invoice);
        order.setTotalAmount(invoice.getTotalAmount());
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
    public List<OrderItemOutDto> getOrderByUser(UUID id) {
        List<Order> orders = orderRepository.findOrderByUserId(id);
        return orders.stream().map(order -> modelMapper.map(order, OrderItemOutDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderOutDto> getOrderByStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);
        return orders.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrderByUserIdAndFoodItemsItemId(UUID userId, UUID itemId) {
        return orderRepository.findOrderByUserIdAndFoodItemsItemId(userId, itemId);
    }

    @Override
    public OrderOutDto updateStatus(UUID orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.setStatus(orderStatus);
        order = orderRepository.save(order);
        return convertEntityToDto(order);
    }

    public void getDailyReport(){
        List<Order> orders = orderRepository.findAll();
        HashMap<LocalDate,Double> report = new HashMap<>();
        HashMap<LocalDate,Integer> dailyCount = new HashMap<>();
        for(Order order : orders){
            LocalDate date = order.getOrderedTime().toLocalDate();
            report.merge(date, order.getTotalAmount(), Double::sum);
            dailyCount.merge(date, 1, Integer::sum);
        }
//        HashMap<LocalDate,List<Double>> reportStatus = new HashMap<>();
//        for(LocalDate key : report.keySet()){
//            reportStatus.put(key,List.of());
//            System.out.println(key+" "+);
//            System.out.println(key+" "+dailyCount.get(key));
//        }
    }

    public Order convertDtoToEntity(OrderInDto input){
        return modelMapper.map(input,Order.class);
    }

    public OrderOutDto convertEntityToDto(Order order){
        return modelMapper.map(order,OrderOutDto.class);
    }
}
