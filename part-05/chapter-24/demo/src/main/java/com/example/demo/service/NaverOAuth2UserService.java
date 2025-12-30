package com.example.demo.service;

import com.example.demo.domain.DemoAuthenticatedUser;
import com.example.demo.domain.LoginType;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class NaverOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public NaverOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, String> response = oAuth2User.getAttribute("response");
        Optional<UserEntity> optionalUser = userRepository.findBySubjectAndLoginType(
                response.get("id"),
                LoginType.NAVER
        );
        UserEntity userEntity = optionalUser
                .orElseGet(() -> userRepository.save(
                        new UserEntity(
                                UUID.randomUUID(),
                                response.get("id"),
                                LoginType.NAVER
                        )
                ));
        return new DemoAuthenticatedUser(
                oAuth2User,
                userEntity.getId(),
                response.get("name"),
                response.get("email")
        );
    }
}
