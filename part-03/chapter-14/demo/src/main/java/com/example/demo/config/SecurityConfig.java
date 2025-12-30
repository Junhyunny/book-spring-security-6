package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers(HttpMethod.GET, "/users")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
        );
        httpSecurity.formLogin(
                Customizer.withDefaults()
        );
        httpSecurity.httpBasic(
                Customizer.withDefaults()
        );
        httpSecurity.csrf(
                AbstractHttpConfigurer::disable
        );
        return httpSecurity.build();
    }
}
