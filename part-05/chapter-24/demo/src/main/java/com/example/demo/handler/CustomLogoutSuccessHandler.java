package com.example.demo.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final String redirectUrl;

    public CustomLogoutSuccessHandler() {
        this("http://localhost:8080/login");
    }

    public CustomLogoutSuccessHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        if (authentication instanceof OAuth2AuthenticationToken oauth2Authentication) {
            if ("google".equals(oauth2Authentication.getAuthorizedClientRegistrationId())) {
                response.sendRedirect(
                        "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=" + redirectUrl
                );
                return;
            }
        }
        response.sendRedirect(redirectUrl);
    }
}
