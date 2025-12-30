package com.example.demo.domain;

import com.example.demo.handler.EmailMaskingMethodAuthorizationDeniedHandler;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

public class User {
    private final String email;

    public User(String email) {
        this.email = email;
    }

    @PostAuthorize(value = "hasAuthority('USER::READ')")
    @HandleAuthorizationDenied(handlerClass = EmailMaskingMethodAuthorizationDeniedHandler.class)
    public String getEmail() {
        return email;
    }
}