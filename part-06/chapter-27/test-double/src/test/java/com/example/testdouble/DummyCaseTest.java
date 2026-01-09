package com.example.testdouble;

import com.example.testdouble.domain.Order;
import com.example.testdouble.domain.User;
import com.example.testdouble.repository.OrderRepository;
import com.example.testdouble.service.OrderService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DummyOrderRepository implements OrderRepository {

    @Override
    public UUID placeOrder(Order order) {
        return null;
    }

    @Override
    public Order findById(UUID id) {
        return null;
    }
}

public class DummyCaseTest {

    @Test
    void givenNotAdmin_whenPlaceOrder_thenThrowException() {
        OrderService sut = new OrderService(new DummyOrderRepository());


        assertThrows(RuntimeException.class, () -> {
            sut.placeOrder(
                    new User("junhyunny", "ROLE_USER"),
                    new Order()
            );
        });
    }
}
