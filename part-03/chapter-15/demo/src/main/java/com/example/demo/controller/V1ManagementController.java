package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path = "/v1/management")
public class V1ManagementController {

    private final AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, String> employees;

    public V1ManagementController() {
        employees = new ConcurrentHashMap<>();
        employees.put(id.incrementAndGet(), "junhyunny");
        employees.put(id.incrementAndGet(), "tangerine");
        employees.put(id.incrementAndGet(), "jua");
        employees.put(id.incrementAndGet(), "tory");
    }

    @GetMapping("/admin")
    public String systemAdmin() {
        return "Junhyunny";
    }

    @GetMapping("/employees")
    public Map<Integer, String> getAllEmployees() {
        return employees;
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Integer id
    ) {
        var name = employees.get(id);
        if (name == null) {
            throw new RuntimeException("employee not found");
        }
        return String.format("%s searches %s(id: %s)'s employee information\n", user.getUsername(), name, id);
    }

    @PostMapping("/employees")
    public void addEmployee(
            @AuthenticationPrincipal User user,
            @RequestBody String name
    ) {
        var newId = id.incrementAndGet();
        employees.put(newId, name);
        System.out.printf("%s creates %s(id: %s)'s employee information\n", user.getUsername(), name, newId);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Integer id
    ) {
        var name = employees.remove(id);
        if (name == null) {
            throw new RuntimeException("employee not found");
        }
        System.out.printf("%s deletes %s(id: %s)'s employee information\n", user.getUsername(), name, id);
    }
}
