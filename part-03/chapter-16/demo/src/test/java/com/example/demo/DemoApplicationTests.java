package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    OrderService orderService;

    @Test
    void test_log_aspect() {
        orderService.createOrder(new Product(1, "computer", 4000000));
    }
}