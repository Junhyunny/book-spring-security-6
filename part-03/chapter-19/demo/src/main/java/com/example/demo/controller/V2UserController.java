package com.example.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class V2UserController {

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/users")
    public List<String> users() {
        return List.of("junhyunny");
    }

    @PostAuthorize("returnObject == authentication.principal.username")
    @GetMapping("/users/me")
    public String me() {
        return "tangerine";
    }
}
