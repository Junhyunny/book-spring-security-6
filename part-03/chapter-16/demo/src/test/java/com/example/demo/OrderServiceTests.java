package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductOrder;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTests {

    @Autowired
    OrderService sut;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void test_log_aspect() {
        sut.createOrder(new Product(1, "computer", 4000000));
    }

    @Test
    void withoutAuthentication_getOrder_thenAuthenticationCredentialsNotFoundException() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> sut.getOrder(1));
    }

    @Test
    void withWrongAuthentication_getOrder_thenAccessDeniedException() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("tangerine", "12345")
                )
        );


        assertThrows(AccessDeniedException.class, () -> sut.getOrder(1));
    }

    @Test
    void withCorrectAuthentication_getOrder_returnValue() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345", Collections.emptyList())
                )
        );


        var result = sut.getOrder(1);


        assertEquals(1L, result.id());
        assertEquals(100L, result.productId());
        assertEquals("junhyunny", result.userId());
        assertTrue(result.delivered());
    }

    @Test
    void getOrders_returnFilteredList() {
        var result = sut.getOrders(new ArrayList<>(Arrays.asList(1L, 2L, 3L, 6L)));


        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(101L, result.get(0).productId());
        assertEquals("junhyunny", result.get(0).userId());
        assertFalse(result.get(0).delivered());

        assertEquals(2L, result.get(1).id());
        assertEquals(102L, result.get(1).productId());
        assertEquals("junhyunny", result.get(1).userId());
        assertFalse(result.get(1).delivered());
    }

    @Test
    void overLimitSize_getOrders_returnEmptyList() {
        var result = sut.getOrders(new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L)));


        assertEquals(0, result.size());
    }

    @Test
    void withoutAuthentication_getLatestOrder_thenAuthenticationCredentialsNotFoundException() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> sut.getLatestOrder(1));
    }

    @Test
    void withNotAuthenticatedCorrectUser_getLatestOrder_thenAccessDeniedException() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345")
                )
        );


        var result = sut.getLatestOrder(1);


        assertNull(result);
    }

    @Test
    void withAuthenticatedWrongUser_getLatestOrder_thenAccessDeniedException() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("tangerine", "12345", Collections.emptyList())
                )
        );


        var result = sut.getLatestOrder(1);


        assertNull(result);
    }

    @Test
    void withCorrectAuthentication_getLatestOrder_returnValue() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345", Collections.emptyList())
                )
        );


        var result = sut.getLatestOrder(1);


        assertEquals(1L, result.id());
        assertEquals(100L, result.productId());
        assertEquals("junhyunny", result.userId());
        assertTrue(result.delivered());
    }

    @Test
    void updateOrders() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345", Collections.emptyList())
                )
        );


        var result = sut.updateOrders(
                new ArrayList<>(
                        List.of(
                                new ProductOrder(
                                        1,
                                        100,
                                        "junhyunny",
                                        true
                                ),
                                new ProductOrder(
                                        1,
                                        100,
                                        "tangerine",
                                        true
                                )
                        )
                )
        );


        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        assertEquals(100L, result.get(0).productId());
        assertEquals("junhyunny", result.get(0).userId());
        assertTrue(result.get(0).delivered());
    }
}
