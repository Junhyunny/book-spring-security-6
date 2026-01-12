package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            PersistentTokenRepository tokenRepository
    ) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(toH2Console()).permitAll()
                        .anyRequest().authenticated()
        );
        httpSecurity.formLogin(
                configurer -> configurer
                        .defaultSuccessUrl("/home")
                        .loginPage("/login")
        );
        httpSecurity.rememberMe(
                configurer -> configurer.tokenRepository(tokenRepository)
        );
        httpSecurity.csrf(
                AbstractHttpConfigurer::disable
        );
        httpSecurity.headers(
                configurer -> configurer.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                )
        );
        return httpSecurity.build();
    }
}