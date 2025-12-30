package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class UserEntity {
    @Id
    private UUID id;
    private String subject;
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;

    public UserEntity(UUID id, String subject, LoginType loginType) {
        this.id = id;
        this.subject = subject;
        this.loginType = loginType;
    }

    public UserEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public LoginType getLoginType() {
        return loginType;
    }
}
