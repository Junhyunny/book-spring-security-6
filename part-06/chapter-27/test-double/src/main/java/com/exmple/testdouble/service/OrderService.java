package com.exmple.testdouble.service;

import com.exmple.testdouble.domain.Order;
import com.exmple.testdouble.domain.User;
import com.exmple.testdouble.repository.OrderRepository;

import java.util.UUID;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public UUID placeOrder(User user, Order order) {
        if (!user.isAdmin()) {
            throw new RuntimeException("only admins can place orders");
        }
        return orderRepository.placeOrder(order);
    }

    public Order findById(UUID id) {
        var order = orderRepository.findById(id);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        return order;
    }
}
