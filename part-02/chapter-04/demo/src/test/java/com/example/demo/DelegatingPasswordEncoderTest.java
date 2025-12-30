package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DelegatingPasswordEncoderTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG",
            "{noop}password",
            "{pbkdf2}5d923b44a6d129f3ddf3e3c8d29412723dcbde72445e8ef6bf3b508fbf17fa4ed4d6b99ca763d8dc",
            "{scrypt}$e0801$8bWJaSu2IKSn9Z9kM+TPXfOc/9bdYSrN1oD9qfVThWEwdRTnO7re7Ei+fUZRJ68k9lTyuTeUp4of4g24hHnazw==$OAOec05+bXxvuu/1qZ6NUR+xQYvYv7BeL1QxwRpY5Pc=",
            "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0"
    })
    void encodeAndMatch(String encodedValue) {

        var sut = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        assertTrue(sut.matches("password", encodedValue));
    }

    @Test
    void without_id_then_throw() {

        var sut = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        assertThrows(
                IllegalArgumentException.class,
                () -> sut.matches(
                        "password",
                        "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"
                )
        );
    }

    @Test
    void change_default_password_encoder() {

        var sut = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        sut.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());


        assertTrue(
                sut.matches(
                        "password",
                        "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"
                )
        );
    }
}
