package com.example.demo;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService sut;

    @Test
    void notAuthorized() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345", Collections.emptyList())
                )
        );


        var result = sut.getUser();


        System.out.println(result.getEmail());
    }

    @Test
    void authorized() {
        SecurityContextHolder.setContext(
                new SecurityContextImpl(
                        new TestingAuthenticationToken("junhyunny", "12345", List.of(new SimpleGrantedAuthority("USER::READ")))
                )
        );


        var result = sut.getUser();


        System.out.println(result.getEmail());
    }
}
