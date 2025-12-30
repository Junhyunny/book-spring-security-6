package com.example.demo.domain;

public record ProductOrder(
        long id,
        long productId,
        String userId,
        boolean delivered
) {
}
