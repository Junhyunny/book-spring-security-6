package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class V1UserController {

    @GetMapping("/users")
    public List<String> users() {
        return List.of("junhyunny");
    }
}
