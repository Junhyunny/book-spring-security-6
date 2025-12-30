package com.example.demo.service;

import com.example.demo.domain.model.Signup;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(Signup requestBody) {
        var user = requestBody.toEntity(passwordEncoder::encode); // 1
        userRepository.save(user); // 2
    }
}