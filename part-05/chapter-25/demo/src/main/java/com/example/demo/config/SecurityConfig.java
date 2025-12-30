package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            ClientRegistrationRepository clientRegistrationRepository
    ) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers("/*/oauth2/code/**").permitAll()
                        .anyRequest().authenticated()
        );
        var authorizedClientRepository = new HttpSessionOAuth2AuthorizedClientRepository();
        var authorizedClientService = new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
        var authorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository,
                "/oauth2/authorization"
        );
        var authorizationRequestRepository = new HttpSessionOAuth2AuthorizationRequestRepository();
        var accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        var authorizationRedirectStrategy = new DefaultRedirectStrategy();
        httpSecurity.oauth2Client(
                configurer -> configurer
                        .authorizationCodeGrant(
                                grantConfigurer -> grantConfigurer
                                        .authorizationRequestResolver(authorizationRequestResolver)
                                        .authorizationRequestRepository(authorizationRequestRepository)
                                        .accessTokenResponseClient(accessTokenResponseClient)
                                        .authorizationRedirectStrategy(authorizationRedirectStrategy)
                        )
                        .authorizedClientService(authorizedClientService)
                        .authorizedClientRepository(authorizedClientRepository)
                        .clientRegistrationRepository(clientRegistrationRepository)
        );
        httpSecurity.oauth2Client(
                Customizer.withDefaults()
        );
        return httpSecurity.build();
    }
}