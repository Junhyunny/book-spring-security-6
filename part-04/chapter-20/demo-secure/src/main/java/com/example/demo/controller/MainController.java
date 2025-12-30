package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final InMemoryUserDetailsManager userManager;

    public MainController(InMemoryUserDetailsManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public ModelAndView index(
            @AuthenticationPrincipal UserDetails principal
    ) {
        var user = userManager.loadUserByUsername(
                principal.getUsername()
        );
        var mav = new ModelAndView("index");
        mav.addObject("username", user.getUsername());
        mav.addObject(
                "role",
                user.getAuthorities()
                        .stream()
                        .findFirst()
                        .orElseThrow()
                        .getAuthority()
        );
        return mav;
    }
}
