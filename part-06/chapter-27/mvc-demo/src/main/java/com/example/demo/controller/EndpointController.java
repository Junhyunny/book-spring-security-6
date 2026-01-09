package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EndpointController {

    @GetMapping("/user")
    public String user() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        System.out.println(authentication);
        return "ok";
    }

    @PostMapping("/user")
    public String postUser() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        System.out.println(authentication);
        return "ok";
    }

    @GetMapping("/admin")
    public String admin() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        System.out.println(authentication);
        return "ok";
    }
}
