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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@Profile("form-login")
@Service
class StubFormLoginUserService implements UserDetailsService {
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

@SpringBootTest(properties = {"spring.profiles.active=form-login"})
@AutoConfigureMockMvc
public class FormLoginRequestTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenFormLogin_thenRedirectIndex() throws Exception {
        mockMvc.perform(
                formLogin()
                        .user("junhyunny")
                        .password("12345")
        ).andExpect(
                redirectedUrl("/")
        );
    }

    @Test
    void givenCustomLoginPath_whenFormLogin_thenRedirectIndex() throws Exception {
        mockMvc.perform(
                formLogin("/login")
                        .user("junhyunny")
                        .password("12345")
        ).andExpect(
                redirectedUrl("/")
        );
    }

    @Test
    void givenCustomParameterName_whenFormLogin_thenRedirectIndex() throws Exception {
        mockMvc.perform(
                formLogin("/login")
                        .user("username", "junhyunny")
                        .password("password", "12345")
        ).andExpect(
                redirectedUrl("/")
        );
    }

    @Test
    void givenWrongUser_whenFormLogin_thenRedirectLoginPage() throws Exception {
        mockMvc.perform(
                formLogin()
                        .user("user")
                        .password("12345")
        ).andExpect(
                redirectedUrl("/login?error")
        );
    }

    @Test
    void givenWrongPassword_whenFormLogin_thenRedirectLoginPage() throws Exception {
        mockMvc.perform(
                formLogin()
                        .user("junhyunny")
                        .password("wrong-password")
        ).andExpect(
                redirectedUrl("/login?error")
        );
    }
}
