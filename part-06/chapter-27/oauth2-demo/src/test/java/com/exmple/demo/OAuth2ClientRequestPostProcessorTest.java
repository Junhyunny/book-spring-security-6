package com.exmple.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType.BEARER;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Client;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OAuth2ClientRequestPostProcessorTest {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/client")
                        .with(oauth2Login())
                        .with(oauth2Client("google"))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomAccessToken_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/client")
                        .with(oauth2Login())
                        .with(oauth2Client("google")
                                .accessToken(
                                        new OAuth2AccessToken(
                                                BEARER,
                                                "custom-token",
                                                null,
                                                null,
                                                Collections.singleton("message:read")
                                        )
                                )
                        )
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    void givenCustomClientRegistration_whenGetUser_thenIsOk() throws Exception {
        mockMvc.perform(
                get("/api/user/client")
                        .with(oauth2Login())
                        .with(oauth2Client()
                                .principalName("junhyunny")
                                .clientRegistration(
                                        clientRegistrationRepository
                                                .findByRegistrationId("google")
                                )
                        )
        ).andExpect(
                status().isOk()
        );
    }
}
