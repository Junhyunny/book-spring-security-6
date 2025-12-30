package com.exmple.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EndpointController {

    @GetMapping("/user/oidc")
    public String oidcUser(
            @AuthenticationPrincipal OidcUser oidcUser
    ) {
        System.out.printf("TokenValue - %s\n", oidcUser.getIdToken().getTokenValue());
        System.out.printf("UserInfo - %s\n", oidcUser.getUserInfo());
        System.out.printf("Claims - %s\n", oidcUser.getClaims());
        System.out.printf("Authorities - %s\n", oidcUser.getAuthorities());
        return "ok";
    }

    @GetMapping("/user/oauth2")
    public String oauth2User(
            @AuthenticationPrincipal OAuth2User oauth2User
    ) {
        System.out.println(oauth2User);
        return "ok";
    }

    @GetMapping("/user/client")
    public String oauth2Client(
            @RegisteredOAuth2AuthorizedClient("google")
            OAuth2AuthorizedClient authorizedClient
    ) {
        System.out.printf("ClientId - %s\n", authorizedClient.getClientRegistration().getClientId());
        System.out.printf("ClientSecret - %s\n", authorizedClient.getClientRegistration().getClientSecret());
        System.out.printf("Principal - %s\n", authorizedClient.getPrincipalName());
        System.out.printf("AccessToken - %s\n", authorizedClient.getAccessToken().getTokenValue());
        System.out.printf("Scopes - %s\n", authorizedClient.getAccessToken().getScopes());
        return "ok";
    }
}
