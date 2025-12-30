package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/management")
public class V1ManagementController {

    @GetMapping("/admin")
    public String systemAdmin() {
        return "paul";
    }

    @GetMapping("/departments")
    public List<String> getAllDepartments() {
        return List.of("HR", "Development", "Design");
    }

    @GetMapping("/employees")
    public List<String> getAllEmployees() {
        return List.of("junhyunny", "jua");
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable("id") String id) {
        return String.format("%s's employee information", id);
    }
}
