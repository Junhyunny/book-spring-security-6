package com.example.demo.domain.model;

import com.example.demo.domain.entity.UserEntity;

import java.util.function.UnaryOperator;

public record Signup(
        String username,
        String password
) {
    public UserEntity toEntity(UnaryOperator<String> encode) {
        return new UserEntity(username, encode.apply(password));
    }
}