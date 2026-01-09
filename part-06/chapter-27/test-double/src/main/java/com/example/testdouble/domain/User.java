package com.example.testdouble.domain;

public record User(
        String username,
        String role
) {
    public boolean isAdmin(){
        return "ROLE_ADMIN".equals(role);
    }
}
