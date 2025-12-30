package com.exmple.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CsrfRequestPostProcessorTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenWithoutCsrfToken_whenPostUser_thenIsForbidden() throws Exception {
        mockMvc.perform(
                post("/api/user")
                        .with(user("user"))
        ).andExpect(
                status().isForbidden()
        );
    }

    @Test
    void givenWithCsrfToken_whenPostUser_thenIsOk() throws Exception {
        mockMvc.perform(
                post("/api/user")
                        .with(user("user"))
                        .with(csrf())
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenWithCsrfTokenInHeader_whenPostUser_thenIsOk() throws Exception {
        mockMvc.perform(
                post("/api/user")
                        .with(user("user"))
                        .with(csrf().asHeader())
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenWithInvalidCsrfToken_whenPostUser_thenIsForbidden() throws Exception {
        mockMvc.perform(
                post("/api/user")
                        .with(user("user"))
                        .with(csrf().useInvalidToken())
        ).andExpect(
                status().isForbidden()
        );
    }
}
