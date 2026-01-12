package com.example.demo.controller;

import com.example.demo.domain.model.SignUp;
import com.example.demo.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SignUpService signupService;

    public SignUpController(SignUpService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(SignUp requestBody) {
        signupService.signup(requestBody);
        return "redirect:/login";
    }
}