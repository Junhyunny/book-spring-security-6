package com.example.demo.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @PreAuthorize("hasRole('USER')")
    @PostAuthorize("returnObject.contains('Hello')")
    public String foo() {
        return "Hello World";
    }
}

