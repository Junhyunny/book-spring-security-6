package com.example.demo.repository;

import com.example.demo.domain.KeyCloakUserMe;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class KeyCloakRepository {

    private final Map<String, KeyCloakUserMe> store = new ConcurrentHashMap<>();

    public void save(String username, KeyCloakUserMe user) {
        store.put(username, user);
    }

    public KeyCloakUserMe get(String username) {
        return store.get(username);
    }

    public void remove(String username) {
        store.remove(username);
    }
}
