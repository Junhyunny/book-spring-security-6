package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.meta.WithRememberMeUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WithRememberMeTest {

    @Autowired
    UserService sut;

    @WithRememberMeUser
    @Test
    void whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }

    @WithRememberMeUser(username = "admin", roles = {"ADMIN"})
    @Test
    void givenAdmin_whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }
}