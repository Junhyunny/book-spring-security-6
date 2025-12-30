package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Service
class SecuredService {
    @Secured(value = {"ROLE_USER", "ROLE_ADMIN", "SECURED::READ"})
    public String get() {
        return "secured resource";
    }

    @Secured(value = {"ROLE_ADMIN"})
    public int delete() {
        return 1;
    }
}

@SpringBootTest
public class SecuredAnnotationTests {

    @Autowired
    SecuredService sut;

    @Test
    @WithMockUser
    void hasUserRole_get_success() {
        var result = sut.get();


        assertEquals("secured resource", result);
    }

    @Test
    @WithMockUser(authorities = {"SECURED::READ"})
    void hasReadAuthority_get_success() {
        var result = sut.get();


        assertEquals("secured resource", result);
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void user_delete_throwsAuthorizationDeniedException() {
        assertThrows(AuthorizationDeniedException.class, () -> sut.delete());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void admin_delete_success() {
        var result = sut.delete();


        assertEquals(1, result);
    }
}
