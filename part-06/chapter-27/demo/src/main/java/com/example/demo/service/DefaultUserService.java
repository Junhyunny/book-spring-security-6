package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @PreAuthorize("isAuthenticated()")
    @Override
    public User getUser(String username) {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        System.out.println(authentication);
        return new User(username, "HR");
    }
}
