package com.example.testdouble.repository;

import com.example.testdouble.domain.Order;

import java.util.UUID;

public interface OrderRepository {
    UUID placeOrder(Order order);

    Order findById(UUID id);
}
