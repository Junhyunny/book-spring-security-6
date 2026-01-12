package com.example.demo.controller;

import com.example.demo.client.KeyCloakClient;
import com.example.demo.domain.KeyCloakUser;
import com.example.demo.repository.KeyCloakRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final KeyCloakClient keyCloakClient;
    private final KeyCloakRepository keyCloakRepository;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public MainController(
            KeyCloakClient keyCloakClient,
            KeyCloakRepository keyCloakRepository,
            OAuth2AuthorizedClientManager authorizedClientManager
    ) {
        this.keyCloakClient = keyCloakClient;
        this.keyCloakRepository = keyCloakRepository;
        this.authorizedClientManager = authorizedClientManager;
    }

    @GetMapping
    public ModelAndView index(
            @RegisteredOAuth2AuthorizedClient("key-cloak") OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal User user
    ) {
        var mav = new ModelAndView("index");
        mav.addObject("username", user.getUsername());
        if (authorizedClient != null) {
            var keyCloakUser = keyCloakRepository.get(user.getUsername());
            mav.addObject("keyCloakUser", keyCloakUser);
        }
        return mav;
    }

    @PostMapping("/authorization")
    public String authorization(
            Authentication authentication,
            HttpServletRequest httpServletRequest
    ) {
        var oauth2AuthorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("key-cloak")
                .principal(authentication)
                .attribute(
                        HttpServletRequest.class.getName(),
                        httpServletRequest
                )
                .build();
        OAuth2AuthorizedClient oauth2AuthorizedClient;
        try {
            oauth2AuthorizedClient = authorizedClientManager.authorize(oauth2AuthorizeRequest);
            if (oauth2AuthorizedClient == null) {
                return "redirect:/error";
            }
        } catch (Exception e) {
            return "redirect:/error";
        }
        var accessToken = oauth2AuthorizedClient.getAccessToken();
        var tokenValue = accessToken.getTokenValue();
        var keyCloakUser = keyCloakClient.userMe(tokenValue);
        var user = (User) authentication.getPrincipal();
        keyCloakRepository.save(user.getUsername(), keyCloakUser);
        return "redirect:/";
    }

    private List<KeyCloakUser> getUsers(OAuth2AuthorizedClient authorizedClient) {
        var accessToken = authorizedClient.getAccessToken();
        var tokenValue = accessToken.getTokenValue();
        return keyCloakClient.users(tokenValue);
    }

    @GetMapping("/users")
    public ModelAndView users(
            @RegisteredOAuth2AuthorizedClient("key-cloak") OAuth2AuthorizedClient authorizedClient,
            Authentication authentication
    ) {
        var mav = new ModelAndView("index::#users");
        try {
            Thread.sleep(5500);
            var users = getUsers(authorizedClient);
            mav.addObject("users", users);
        } catch (Exception e) {
            System.out.printf("사용자 조회 시 에러 발생. 리프레시 토큰을 사용한 재인증(원인: %s)\n", e.getMessage());
            var reauthorizedClient = reauthorization(authorizedClient, authentication);
            if (reauthorizedClient == null) {
                var user = (User) authentication.getPrincipal();
                keyCloakRepository.remove(user.getUsername());
                return new ModelAndView("error");
            } else {
                mav.addObject("users", getUsers(reauthorizedClient));
            }
        }
        return mav;
    }

    private OAuth2AuthorizedClient reauthorization(
            OAuth2AuthorizedClient authorizedClient,
            Authentication authentication
    ) {
        var oauth2AuthorizeRequest = OAuth2AuthorizeRequest
                .withAuthorizedClient(authorizedClient)
                .principal(authentication)
                .build();
        try {
            return authorizedClientManager.authorize(oauth2AuthorizeRequest);
        } catch (Exception e) {
            return null;
        }
    }
}
