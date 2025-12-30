package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http.authorizeHttpRequests(
                registry -> registry.anyRequest().authenticated()
        );
        http.oauth2Login(
                configurer -> configurer
                        .authorizationEndpoint(
                                endpointConfig -> endpointConfig
                                        .baseUri("/custom/oauth2/authorization")
                        )
                        .redirectionEndpoint(
                                endpointConfig -> endpointConfig
                                        .baseUri("/**/oauth2/code/**")
                        )
        );
        return http.build();
    }
}
