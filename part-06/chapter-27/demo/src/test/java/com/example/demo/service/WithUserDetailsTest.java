package com.example.demo.service;

import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WithUserDetailsTest {

    @Autowired
    UserService sut;

    @Test
    @WithUserDetails
    void whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }

    @Test
    @WithUserDetails(
            value = "admin",
            userDetailsServiceBeanName = "defaultUserDetailsService"
    )
    void givenAdmin_whenGetUser_thenReturnUser() {
        assertEquals(
                new User("junhyunny", "HR"),
                sut.getUser("junhyunny")
        );
    }
}