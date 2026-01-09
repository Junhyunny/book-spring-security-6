package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomRequestProcessor {

    public static RequestPostProcessor junhyunny() {
        return user("junhyunny").roles("ADMIN");
    }
}

@SpringBootTest
@AutoConfigureMockMvc
public class UserRequestPostProcessorTest {

    @Autowired
    MockMvc mockMvc;

    UserDetails userDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

    Authentication rememberMeAuthentication = new RememberMeAuthenticationToken(
            "remember-me-key",
            userDetails,
            userDetails.getAuthorities()
    );

    SecurityContext securityContext = new SecurityContextImpl(rememberMeAuthentication);

    @Test
    void whenGetUser_thenIsUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/user")
        ).andExpect(
                status().isUnauthorized()
        );
    }

    @Test
    @WithMockUser
    void givenWithMockUser_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenUser_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(user("user"))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenAdmin_whenGetAdmin_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/admin")
                        .with(
                                user("admin")
                                        .password("pass")
                                        .roles("ADMIN")
                        )
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenUserDetails_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(user(userDetails))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenAnonymous_whenGetUser_thenIsUnauthorized() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(anonymous())
        ).andExpect(
                status().isUnauthorized()
        );
    }

    @Test
    void givenAuthentication_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(authentication(rememberMeAuthentication))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenSecurityContext_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user")
                        .with(securityContext(securityContext))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenJunhyunny_whenGetAdmin_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/admin")
                        .with(CustomRequestProcessor.junhyunny())
        ).andExpect(
                status().isOk()
        );
    }
}
