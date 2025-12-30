package com.example.demo.factory;

import com.example.demo.meta.WithRememberMeUser;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithRememberMeUserFactory
        implements WithSecurityContextFactory<WithRememberMeUser> {

    @Override
    public SecurityContext createSecurityContext(WithRememberMeUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UserDetails principal = User.withDefaultPasswordEncoder()
                .username(annotation.username())
                .password("12345")
                .roles(annotation.roles())
                .build();
        Authentication authentication = new RememberMeAuthenticationToken(
                "custom-remember-key",
                principal,
                principal.getAuthorities()
        );
        context.setAuthentication(authentication);
        return context;
    }
}
