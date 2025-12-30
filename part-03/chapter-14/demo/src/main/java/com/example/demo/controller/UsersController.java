package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @GetMapping
    public String getUsers() {
        return "getUsers";
    }

    @PostMapping
    public String postUsers() {
        return "postUsers";
    }

    @PutMapping
    public String putUsers() {
        return "putUsers";
    }

    @DeleteMapping
    public String deleteUsers() {
        return "deleteUsers";
    }

}
