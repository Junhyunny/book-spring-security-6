package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("junhyunny")
                        .password("12345")
                        .roles("ADMIN")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("jua")
                        .password("12345")
                        .roles("USER")
                        .build()
        );
        return inMemoryUserDetailsManager;
    }
}
