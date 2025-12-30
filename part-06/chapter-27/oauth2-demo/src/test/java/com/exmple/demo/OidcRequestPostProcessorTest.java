package com.exmple.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OidcRequestPostProcessorTest {

    OidcUser oidcUser = new DefaultOidcUser(
            AuthorityUtils.createAuthorityList("SCOPE_user:read"),
            OidcIdToken
                    .withTokenValue("custom-id-token")
                    .claim("user_name", "junhyunny")
                    .build(),
            OidcUserInfo.builder()
                    .email("junhyunny@gmail.com")
                    .build(),
            "user_name"
    );

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oidc")
                        .with(oidcLogin())
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomAuthorities_whenGetUser_thenIsOk() throws Exception {
        var scopeUserRead = new SimpleGrantedAuthority("SCOPE_user:read");
        mockMvc.perform(
                get("/api/user/oidc")
                        .with(oidcLogin().authorities(scopeUserRead))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomClaims_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oidc")
                        .with(oidcLogin().idToken(
                                token -> token.claim("user_id", "junhyunny")
                        ))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomOidcUser_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/oidc")
                        .with(oidcLogin().oidcUser(oidcUser))
        ).andExpect(
                status().isOk()
        );
    }
}
