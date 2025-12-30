package com.example.demo.client;

import com.example.demo.domain.KeyCloakUser;
import com.example.demo.domain.KeyCloakUserMe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class KeyCloakClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String adminBaseUrl;

    public KeyCloakClient(
            @Value("${key-cloak.host}") String host
    ) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = host + "/realms/spring-security-oauth2-example";
        this.adminBaseUrl = host + "/admin/realms/spring-security-oauth2-example";
    }

    public KeyCloakUserMe userMe(String accessToken) {
        var requestEntity = RequestEntity.method(
                        HttpMethod.GET,
                        baseUrl + "/protocol/openid-connect/userinfo"
                )
                .header("Authorization", "Bearer " + accessToken)
                .build();
        return restTemplate
                .exchange(requestEntity, KeyCloakUserMe.class)
                .getBody();
    }

    public List<KeyCloakUser> users(String accessToken) {
        var requestEntity = RequestEntity.method(
                        HttpMethod.GET,
                        adminBaseUrl + "/users"
                )
                .header("Authorization", "Bearer " + accessToken)
                .build();
        return restTemplate
                .exchange(
                        requestEntity,
                        new ParameterizedTypeReference<List<KeyCloakUser>>() {}
                )
                .getBody();
    }
}
