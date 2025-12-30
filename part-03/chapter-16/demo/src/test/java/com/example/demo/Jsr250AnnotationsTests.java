package com.example.demo;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Service
class Jsr250AnnotationsService {
    @RolesAllowed(value = {"USER", "ADMIN"})
    public String get() {
        return "secured resource";
    }

    @RolesAllowed(value = {"ADMIN"})
    public int delete() {
        return 1;
    }

    @PermitAll
    public List<String> getAll() {
        return List.of("A", "B", "C");
    }

    @DenyAll
    public void deleteAll() {
    }
}

@SpringBootTest
public class Jsr250AnnotationsTests {

    @Autowired
    Jsr250AnnotationsService sut;

    @Test
    @WithMockUser
    void hasUserRole_get_success() {
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

    @Test
    void withoutRoles_getAll_success() {
        var result = sut.getAll();


        assertEquals(List.of("A", "B", "C"), result);
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "USER"})
    void admin_deleteAll_throwsAuthorizationDeniedException() {
        assertThrows(AuthorizationDeniedException.class, () -> sut.deleteAll());
    }
}
