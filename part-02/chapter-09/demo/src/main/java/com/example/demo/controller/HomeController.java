package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home") // 1
    public ModelAndView home(@AuthenticationPrincipal User user) { // 2
        var modelAndView = new ModelAndView("home"); // 3
        modelAndView.addObject("username", user.getUsername()); // 4
        return modelAndView;
    }
}