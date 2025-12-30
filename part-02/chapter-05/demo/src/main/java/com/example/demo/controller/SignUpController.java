package com.example.demo.controller;

import com.example.demo.domain.model.Signup;
import com.example.demo.service.SignupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SignupService signupService;

    public SignUpController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Signup requestBody) {
        signupService.signup(requestBody);
        return "redirect:/login";
    }
}