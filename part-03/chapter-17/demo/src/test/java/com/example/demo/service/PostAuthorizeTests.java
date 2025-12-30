package com.example.demo.service;

import com.example.demo.TestUsersContext;
import com.example.demo.repository.ManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PostAuthorizeTests extends TestUsersContext {

    @SpyBean
    ManagementRepository repository;

    @Autowired
    V2ManagementService sut;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        repository.init();
    }

    @Test
    void supervisor_getEmployeeByIdForSelf_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        var result = sut.getEmployeeById("S0001");


        assertEquals("S0001", result.id());
        assertEquals("S0001", result.managerId());
        assertEquals("supervisor", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void supervisor_getEmployeeByIdForJunhyunny_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        var result = sut.getEmployeeById("M0001");


        assertEquals("M0001", result.id());
        assertEquals("S0001", result.managerId());
        assertEquals("junhyunny", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }


    @Test
    void supervisor_getEmployeeByIdForTangerine_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        var result = sut.getEmployeeById("M0002");


        assertEquals("M0002", result.id());
        assertEquals("S0001", result.managerId());
        assertEquals("tangerine", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }


    @Test
    void supervisor_getEmployeeByIdForJua_throwAuthorizationDeniedException() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("E0001")
        );
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void junhyunny_getEmployeeByIdForSelf_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        var result = sut.getEmployeeById("M0001");


        assertEquals("M0001", result.id());
        assertEquals("S0001", result.managerId());
        assertEquals("junhyunny", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void junhyunny_getEmployeeByIdForJua_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        var result = sut.getEmployeeById("E0001");


        assertEquals("E0001", result.id());
        assertEquals("M0001", result.managerId());
        assertEquals("jua", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void junhyunny_getEmployeeByIdForTory_throwAuthorizationDeniedException() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("E0002")
        );
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void tangerine_getEmployeeByIdForSelf_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        var result = sut.getEmployeeById("M0002");


        assertEquals("M0002", result.id());
        assertEquals("S0001", result.managerId());
        assertEquals("tangerine", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void tangerine_getEmployeeByIdForTory_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        var result = sut.getEmployeeById("E0002");


        assertEquals("E0002", result.id());
        assertEquals("M0002", result.managerId());
        assertEquals("tory", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void tangerine_getEmployeeByIdForJua_throwAuthorizationDeniedException() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("E0001")
        );
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void jua_getEmployeeByIdForSelf_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        var result = sut.getEmployeeById("E0001");


        assertEquals("E0001", result.id());
        assertEquals("M0001", result.managerId());
        assertEquals("jua", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void jua_getEmployeeByIdForTory_throwAuthorizationDeniedException() {
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("E0002")
        );
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void tory_getEmployeeByIdForSelf_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        var result = sut.getEmployeeById("E0002");


        assertEquals("E0002", result.id());
        assertEquals("M0002", result.managerId());
        assertEquals("tory", result.name());
        verify(repository, times(1)).getEmployeeById(any());
    }

    @Test
    void tory_getEmployeeByIdForTory_throwAuthorizationDeniedException() {
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("E0001")
        );
        verify(repository, times(1)).getEmployeeById(any());
    }
}
