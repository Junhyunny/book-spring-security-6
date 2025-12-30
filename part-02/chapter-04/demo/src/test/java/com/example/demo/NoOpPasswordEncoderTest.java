package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoOpPasswordEncoderTest {

    @Test
    void encodeAndMatch() {
        var sut = NoOpPasswordEncoder.getInstance();


        var result = sut.encode("hello world");


        assertTrue(sut.matches("hello world", result));
        System.out.println(result);
    }
}
