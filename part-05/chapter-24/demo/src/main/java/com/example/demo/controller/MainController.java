package com.example.demo.controller;

import com.example.demo.domain.DemoAuthenticatedUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping
    public ModelAndView index(
            @AuthenticationPrincipal DemoAuthenticatedUser principal
    ) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userName", principal.userName());
        modelAndView.addObject("email", principal.email());
        return modelAndView;
    }
}
