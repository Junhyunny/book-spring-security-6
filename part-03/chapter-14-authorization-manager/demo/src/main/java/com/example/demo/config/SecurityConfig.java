package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_MANAGER > ROLE_USER > ROLE_VISITOR
                WRITE::HR > READ::HR""");
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers("/v1/management/**").denyAll()
                        .requestMatchers("/v2/management/admin").permitAll()
                        .requestMatchers("/v2/management/departments").fullyAuthenticated()
                        .requestMatchers("/v2/management/employees").hasRole("EMPLOYEE")
                        .requestMatchers("/v2/management/employees/**").hasRole("MANAGER")
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
