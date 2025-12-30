package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductOrder;
import com.example.demo.handler.NullMethodAuthorizationDeniedHandler;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public void createOrder(Product product) {
        System.out.println("create order for " + product);
    }

    @PreAuthorize("isAuthenticated()") // 1
    @PostAuthorize("returnObject.userId() == authentication.name") // 2
    public ProductOrder getOrder(long orderId) {
        // ...
        return new ProductOrder(
                orderId,
                100,
                "junhyunny",
                true
        );
    }

    @PreAuthorize("isAuthenticated()") // 1
    @PostAuthorize("returnObject.userId() == authentication.name") // 2
    @HandleAuthorizationDenied(handlerClass = NullMethodAuthorizationDeniedHandler.class) // 3
    public ProductOrder getLatestOrder(long orderId) {
        // ...
        return new ProductOrder(
                orderId,
                100,
                "junhyunny",
                true
        );
    }

    @PreFilter("#orderIdList.size() <= 5") // 1
    @PostFilter("filterObject.delivered() == false") // 2
    public List<ProductOrder> getOrders(List<Long> orderIdList) {
        // ...
        return new ArrayList<>(
                orderIdList.stream()
                        .map(
                                id -> new ProductOrder(
                                        id,
                                        100 + id,
                                        "junhyunny",
                                        id % 3 == 0
                                )
                        )
                        .toList()
        );
    }

    @PreFilter("filterObject.userId == authentication.name") // 1
    public List<ProductOrder> updateOrders(List<ProductOrder> productOrders) {
        // ...
        return new ArrayList<>(productOrders);
    }
}
