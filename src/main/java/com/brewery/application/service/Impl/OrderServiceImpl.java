package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.OrderInDto;
import com.brewery.application.dto.inputdto.OrderItemInDto;
import com.brewery.application.dto.outputdto.*;
import com.brewery.application.entity.*;
import com.brewery.application.enums.OrderStatus;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.*;
import com.brewery.application.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public OrderOutDto createOrder(UUID userId,OrderInDto input) {
        Order order = new Order();
        System.out.println(input.getUserId());
        User user = userRepository.findById(userId).orElseThrow(()->new ElementNotFoundException("Item with given id is not found"));
        List<OrderItemInDto> foodItems = input.getItems();
        List<OrderItem> foodItems1 = new ArrayList<>();
        for(OrderItemInDto item : foodItems){
           OrderItem it = new OrderItem();
           Item item1 = itemRepository.findById(item.getItemId()).orElseThrow(()->new ElementNotFoundException("Item with given id is not found"));
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
        Store store=storeRepository.findById(input.getStoreId()).orElseThrow(()->new ElementNotFoundException("Store with id not found"));
        order.setStore(store);
        order.setFoodItems(foodItems1);
        order.setUserLatitude(input.getUserLatitude());
        order.setUserLongitude(input.getUserLongitude());
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderedTime(LocalDateTime.now());
        order = orderRepository.save(order);
        return convertEntityToDto(order);
    }

    @Override
    public OrderOutDto getOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
        return convertEntityToDto(order);
    }

    @Override
    public OrderTrackingDto trackOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
        return modelMapper.map(order,OrderTrackingDto.class);
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


    public InvoiceOutDto initiatePayment(UUID id){
        Order order = orderRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
        Float Amount = 0.0F;
        Float TotalAmount = 0.0F;
        List<OrderItem> foodItems = order.getFoodItems();
        for(OrderItem foodItem : foodItems){
            Item item = foodItem.getItem();
            Amount+=item.getPrice()*foodItem.getQuantity();
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(Amount);
        invoice.setGst(Amount*0.05F);
        invoice.setDeliveryFee(40.0F);
        invoice.setTotalAmount(Amount+Amount*0.05F+40.0F);
        invoice = invoiceRepository.save(invoice);
        order.setInvoice(invoice);
        order.setTotalAmount(invoice.getTotalAmount());
        orderRepository.save(order);
        return modelMapper.map(invoice,InvoiceOutDto.class);
    }

    @Override
    public OrderOutDto updateOrder(OrderInDto input) {
        Order order = convertDtoToEntity(input);
        Order existingOrder = orderRepository.findById(order.getId()).orElseThrow(()->new ElementNotFoundException("Order with given id is not found"));
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
        Order order = orderRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Order not found with given Id"));
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
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ElementNotFoundException("Order with id is not found"));
        order.setStatus(orderStatus);
        order = orderRepository.save(order);
        return convertEntityToDto(order);
    }

    public DashboardOutputDto getDailyReport(){
        List<Order> orders = orderRepository.findAll();
        HashMap<LocalDate,Float> report = new HashMap<>();
        HashMap<LocalDate,Integer> dailyCount = new HashMap<>();
        Double totalRevenue = 0.0;
        for(Order order : orders){
            LocalDate date = order.getOrderedTime().toLocalDate();
            report.merge(date, order.getTotalAmount(), Float::sum);
            dailyCount.merge(date, 1, Integer::sum);
        }

        //String arr[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};

        List<String> dates = new ArrayList<>();

        List<Float> revenue = new ArrayList<>();

        List<Integer> noOfOrders = new ArrayList<>();

        for(LocalDate key : report.keySet()) {
            dates.add(key.getMonth()+" "+key.getDayOfMonth());
            revenue.add(report.get(key));
            totalRevenue+=report.get(key);
            noOfOrders.add(dailyCount.get(key));
        }
        DashboardOutputDto dto = new DashboardOutputDto();
        dto.setDates(dates);
        dto.setRevenue(revenue);
        dto.setNoOfOrders(noOfOrders);
        dto.setTotalOrders((int)orderRepository.count());
        dto.setTotalCustomers((int)userRepository.count());
        dto.setTotalMenuItems((int)itemRepository.count());
        dto.setTotalRevenue(totalRevenue);

        return dto;
    }

    public Order convertDtoToEntity(OrderInDto input){
        return modelMapper.map(input,Order.class);
    }

    public OrderOutDto convertEntityToDto(Order order){
        return modelMapper.map(order,OrderOutDto.class);
    }
}
