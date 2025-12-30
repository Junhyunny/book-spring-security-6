package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Argon2PasswordEncoderTest {

    @Test
    void encodeAndMatch() {
        var sut = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();


        var result = sut.encode("hello world");


        assertTrue(sut.matches("hello world", result));
        System.out.println(result);
    }
}
