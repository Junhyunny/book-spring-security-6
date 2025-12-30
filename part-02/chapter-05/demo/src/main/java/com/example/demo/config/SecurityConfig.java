package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .anyRequest().authenticated()
        );
        httpSecurity.formLogin(
                configurer -> configurer
                        .defaultSuccessUrl("/home")
                        .loginPage("/login")
                        .loginProcessingUrl("/login/processing")
                        .failureUrl("/login?fail")
        );
        httpSecurity.logout(
                configurer -> configurer.logoutUrl("/logout/processing")
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