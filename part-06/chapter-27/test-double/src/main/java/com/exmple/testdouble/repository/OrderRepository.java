package com.exmple.testdouble.repository;

import com.exmple.testdouble.domain.Order;

import java.util.UUID;

public interface OrderRepository {
    UUID placeOrder(Order order);

    Order findById(UUID id);
}
