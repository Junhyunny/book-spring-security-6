package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyCloakUserMe(
        String sub,
        @JsonProperty("preferred_username")
        String preferredUsername
) {
}
