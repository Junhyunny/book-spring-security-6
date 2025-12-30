package com.exmple.testdouble.domain;

import java.util.UUID;

public record Order(
        UUID id,
        int price
) {
    public Order() {
        this(UUID.randomUUID(), 0);
    }

    public Order(int price) {
        this(UUID.randomUUID(), price);
    }
}
