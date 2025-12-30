package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptPasswordEncoderTest {

    @Test
    void encodeAndMatch() {
        var sut = new BCryptPasswordEncoder();


        var result = sut.encode("hello world");


        assertTrue(sut.matches("hello world", result));
        System.out.println(result);
    }
}
