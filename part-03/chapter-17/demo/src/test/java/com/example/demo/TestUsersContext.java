package com.example.demo;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public abstract class TestUsersContext {

    protected Authentication supervisorAuthentication = new TestingAuthenticationToken(
            "S0001",
            "123",
            List.of(
                    new SimpleGrantedAuthority("ROLE_SUPERVISOR")
            )
    );
    protected Authentication junhyunnyAuthentication = new TestingAuthenticationToken(
            "M0001",
            "123",
            List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER")
            )
    );
    protected Authentication tangerineAuthentication = new TestingAuthenticationToken(
            "M0002",
            "123",
            List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER")
            )
    );
    protected Authentication juaAuthentication = new TestingAuthenticationToken(
            "E0001",
            "123",
            List.of(
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE")
            )
    );
    protected Authentication toryAuthentication = new TestingAuthenticationToken(
            "E0002",
            "123",
            List.of(
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE")
            )
    );
}
