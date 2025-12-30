package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public record DemoAuthenticatedUser(
        OAuth2User oauth2User,
        UUID id,
        String userName,
        String email
) implements OidcUser {

    @Override
    public Map<String, Object> getClaims() {
        if (oauth2User instanceof OidcUser oidcUser) {
            return oidcUser.getClaims();
        }
        return Map.of();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        if (oauth2User instanceof OidcUser oidcUser) {
            return oidcUser.getUserInfo();
        }
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        if (oauth2User instanceof OidcUser oidcUser) {
            return oidcUser.getIdToken();
        }
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }
}
