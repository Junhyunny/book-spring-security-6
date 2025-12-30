package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Pbkdf2PasswordEncoderTest {

    @Test
    void encodeAndMatch() {
        var sut = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();


        var result = sut.encode("hello world");


        assertTrue(sut.matches("hello world", result));
        System.out.println(result);
    }
}
