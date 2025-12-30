package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        var password = passwordEncoder.encode("123");
        var manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.builder()
                        .username("junhyunny")
                        .password(password)
                        .roles("EMPLOYEE")
                        .build()
        );
        manager.createUser(
                User.builder()
                        .username("jua")
                        .password(password)
                        .roles("MANAGER")
                        .build()
        );
        return manager;
    }
}
