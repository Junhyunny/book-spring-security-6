package com.example.demo.controller;

import com.example.demo.service.MainService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public Object index(
            @RegisteredOAuth2AuthorizedClient("naver")
            OAuth2AuthorizedClient oAuth2AuthorizedClient
    ) {
        var accessToken = oAuth2AuthorizedClient.getAccessToken();
        return mainService.getDepartments(
                accessToken.getTokenValue()
        );
    }

    @GetMapping("/naver")
    public OAuth2AuthorizedClient naverIndex(
            @RegisteredOAuth2AuthorizedClient("naver")
            OAuth2AuthorizedClient oAuth2AuthorizedClient
    ) {
        return oAuth2AuthorizedClient;
    }

    @GetMapping("/google")
    public OAuth2AuthorizedClient googleIndex(
            @RegisteredOAuth2AuthorizedClient("google")
            OAuth2AuthorizedClient oAuth2AuthorizedClient
    ) {
        return oAuth2AuthorizedClient;
    }

    @GetMapping("/callback/oauth2/code/naver")
    public OAuth2AuthorizedClient naver(
            @RegisteredOAuth2AuthorizedClient("naver")
            OAuth2AuthorizedClient oAuth2AuthorizedClient
    ) {
        return oAuth2AuthorizedClient;
    }

    @GetMapping("/login/oauth2/code/google")
    public OAuth2AuthorizedClient google(
            @RegisteredOAuth2AuthorizedClient("google")
            OAuth2AuthorizedClient oAuth2AuthorizedClient
    ) {
        return oAuth2AuthorizedClient;
    }
}
