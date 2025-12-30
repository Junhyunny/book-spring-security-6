package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.anyOf;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_MANAGER > ROLE_EMPLOYEE
                MANAGEMENT::WRITE > MANAGEMENT::READ""");
    }

    private <T> AuthorizationManager<T> hasAuthorityWithRoleHierarchy(String authority) {
        var manager = hasAuthority(authority);
        manager.setRoleHierarchy(roleHierarchy());
        return (AuthorizationManager<T>) manager;
    }

    private <T> AuthorizationManager<T> hasRoleWithRoleHierarchy(String role) {
        var manager = hasRole(role);
        manager.setRoleHierarchy(roleHierarchy());
        return (AuthorizationManager<T>) manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        httpSecurity.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers("/v1/management/**").denyAll()
                        .requestMatchers("/v2/management/admin").permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v2/management/employees"
                        ).authenticated()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/v2/management/employees/**"
                        )
                        .access(
                                anyOf(
                                        hasAuthorityWithRoleHierarchy("MANAGEMENT::READ"),
                                        hasRoleWithRoleHierarchy("EMPLOYEE")
                                )
                        )
                        .requestMatchers(
                                "/v2/management/employees/**"
                        )
                        .access(
                                anyOf(
                                        hasAuthorityWithRoleHierarchy("MANAGEMENT::WRITE"),
                                        hasRoleWithRoleHierarchy("MANAGER")
                                )
                        )
                        .anyRequest().denyAll()
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