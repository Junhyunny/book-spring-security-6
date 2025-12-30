package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoleHierarchyTests {

    @Test
    void one_role() {
//        var sut = new RoleHierarchyImpl();
//        sut.setHierarchy("""
//                ROLE_ADMIN > ROLE_MANAGER
//                ROLE_MANAGER > ROLE_USER
//                ROLE_USER > ROLE_VISITOR
//                WRITE::HR > READ::HR""");
//        var sut = RoleHierarchyImpl.withDefaultRolePrefix()
//                .role("ADMIN").implies("MANAGER")
//                .role("MANAGER").implies("USER")
//                .role("USER").implies("VISITOR")
//                .build();
//        var sut = RoleHierarchyImpl.fromHierarchy("""
//                ROLE_ADMIN > ROLE_MANAGER
//                ROLE_MANAGER > ROLE_USER
//                ROLE_USER > ROLE_VISITOR
//                WRITE::HR > READ::HR""");
        var sut = RoleHierarchyImpl.withRolePrefix("")
                .role("ROLE_ADMIN").implies("ROLE_MANAGER")
                .role("ROLE_MANAGER").implies("ROLE_USER")
                .role("ROLE_USER").implies("ROLE_VISITOR")
                .role("WRITE::HR").implies("READ::HR")
                .build();


        var result = sut.getReachableGrantedAuthorities(
                List.of(new SimpleGrantedAuthority("ROLE_MANAGER"))
        );


        assertEquals(result.size(), 3);
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_MANAGER")));
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_VISITOR")));
    }

    @Test
    void one_role_with_authority() {
        var sut = RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_MANAGER > ROLE_USER > ROLE_VISITOR
                WRITE::HR > READ::HR""");


        var result = sut.getReachableGrantedAuthorities(
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("WRITE::HR")
                )
        );


        assertEquals(result.size(), 6);
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_MANAGER")));
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(result.contains(new SimpleGrantedAuthority("ROLE_VISITOR")));
        assertTrue(result.contains(new SimpleGrantedAuthority("WRITE::HR")));
        assertTrue(result.contains(new SimpleGrantedAuthority("READ::HR")));
    }
}
