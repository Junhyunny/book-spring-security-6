package com.example.demo.service;

import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WithMockUserTest {

    @Autowired
    UserService sut;

    @Test
    void givenNotAuthenticated_whenGetUser_thenThrowException() {
        assertThrows(
                AuthenticationCredentialsNotFoundException.class,
                () -> sut.getUser("junhyunny")
        );
    }

    @WithMockUser
    @Test
    void givenAuthenticated_whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void givenAdmin_whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }
}