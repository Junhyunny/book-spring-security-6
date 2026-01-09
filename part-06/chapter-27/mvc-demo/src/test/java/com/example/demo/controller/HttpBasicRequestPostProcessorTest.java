package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("http-basic")
@Service
class StubHttpBasicUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        return User.withDefaultPasswordEncoder()
                .username("junhyunny")
                .password("12345")
                .roles("USER")
                .build();
    }
}

@SpringBootTest(properties = {"spring.profiles.active=http-basic"})
@AutoConfigureMockMvc
public class HttpBasicRequestPostProcessorTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenHttpBasic_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(
                                httpBasic(
                                        "junhyunny",
                                        "12345"
                                )
                        )
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenWrongPassword_whenHttpBasic_thenIsUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(
                                httpBasic(
                                        "junhyunny",
                                        "wrong-password"
                                )
                        )
        ).andExpect(
                status().isUnauthorized()
        ).andExpect(
                header().string(
                        "WWW-Authenticate",
                        "Basic realm=\"Realm\""
                )
        );
    }
}
