package com.example.demo.service;

import com.example.demo.domain.model.SignUp;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignUp requestBody) {
        var user = requestBody.toEntity(passwordEncoder::encode);
        userRepository.save(user);
    }
}