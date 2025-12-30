package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("junhyunny")
                        .password("12345")
                        .roles("USER")
                        .build()
        );
        return inMemoryUserDetailsManager;
    }
}