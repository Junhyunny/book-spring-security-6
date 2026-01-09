package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class LogoutRequestTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenLogout_thenRedirectLoginPage() throws Exception {
        mockMvc.perform(
                logout("/logout")
        ).andExpect(
                redirectedUrl("/login?logout")
        );
    }
}
