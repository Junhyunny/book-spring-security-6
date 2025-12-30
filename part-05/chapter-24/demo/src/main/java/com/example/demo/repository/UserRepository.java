package com.example.demo.repository;

import com.example.demo.domain.LoginType;
import com.example.demo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findBySubjectAndLoginType(String subject, LoginType loginType);
}
