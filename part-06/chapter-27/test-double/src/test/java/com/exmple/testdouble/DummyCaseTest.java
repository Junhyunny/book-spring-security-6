package com.exmple.testdouble;

import com.exmple.testdouble.domain.Order;
import com.exmple.testdouble.domain.User;
import com.exmple.testdouble.repository.OrderRepository;
import com.exmple.testdouble.service.OrderService;
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
