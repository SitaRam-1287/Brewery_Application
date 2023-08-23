package com.brewery.application.repository;

import com.brewery.application.entity.Order;
import com.brewery.application.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    public List<Order> findOrderByUserId(UUID id);

    public List<Order> findOrderByStatus(OrderStatus orderStatus);

}
