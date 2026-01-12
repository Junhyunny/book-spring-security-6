package com.example.demo.controller;

import com.example.demo.domain.RoleCommand;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final InMemoryUserDetailsManager userManager;

    public UserController(InMemoryUserDetailsManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public String updateUserRole(
            @AuthenticationPrincipal UserDetails principal,
            RoleCommand command
    ) {
        var user = userManager.loadUserByUsername(
                principal.getUsername()
        );
        userManager.updateUser(
                User.withUserDetails(user)
                        .authorities(command.role())
                        .build()
        );
        return "redirect:/";
    }
}
