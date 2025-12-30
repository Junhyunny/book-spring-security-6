package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WithAnonymousUserTest {

    @Autowired
    UserService sut;

    @WithAnonymousUser
    @Test
    void givenAnonymous_whenGetUser_thenThrowException() {
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getUser("junhyunny")
        );
    }
}