package com.example.demo.service;

import com.example.demo.domain.DemoAuthenticatedUser;
import com.example.demo.domain.LoginType;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GoogleOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    public GoogleOidcUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Optional<UserEntity> optionalUser = userRepository.findBySubjectAndLoginType(
                oidcUser.getSubject(),
                LoginType.GOOGLE
        );
        UserEntity userEntity = optionalUser
                .orElseGet(() -> userRepository.save(
                        new UserEntity(
                                UUID.randomUUID(),
                                oidcUser.getSubject(),
                                LoginType.GOOGLE
                        )
                ));
        return new DemoAuthenticatedUser(
                oidcUser,
                userEntity.getId(),
                oidcUser.getAttribute("name"),
                oidcUser.getAttribute("email")
        );
    }
}
