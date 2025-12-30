package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @AuthorizeReturnObject
    public User getUser() {
        return new User("junhyunny@naver.com");
    }
}
