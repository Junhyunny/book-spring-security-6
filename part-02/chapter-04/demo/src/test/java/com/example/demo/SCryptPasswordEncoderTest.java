package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SCryptPasswordEncoderTest {

    @Test
    void encodeAndMatch() {
        var sut = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();


        var result = sut.encode("hello world");


        assertTrue(sut.matches("hello world", result));
        System.out.println(result);
    }
}
