package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OAuth2RequestPostProcessorTest {

    OAuth2User oauth2User = new DefaultOAuth2User(
            AuthorityUtils.createAuthorityList("SCOPE_user:read"),
            Collections.singletonMap("user_name", "junhyunny"),
            "user_name"
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oauth2")
                        .with(oauth2Login())
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomAuthorities_whenGetUser_thenIsOk() throws Exception {
        var scopeUserRead = new SimpleGrantedAuthority("SCOPE_user:read");
        mockMvc.perform(
                get("/api/user/oauth2")
                        .with(oauth2Login().authorities(scopeUserRead))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomAttributes_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oauth2")
                        .with(oauth2Login().attributes(
                                attrs -> attrs.put("user_id", "junhyunny")
                        ))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomOAuth2User_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oauth2")
                        .with(oauth2Login().oauth2User(oauth2User))
        ).andExpect(
                status().isOk()
        );
    }
}
