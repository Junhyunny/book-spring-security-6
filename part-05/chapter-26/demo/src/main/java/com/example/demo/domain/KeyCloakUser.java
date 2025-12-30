package com.example.demo.domain;

public record KeyCloakUser(
        String sub,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
