package com.example.demo.service;

import com.example.demo.TestUsersContext;
import com.example.demo.domain.Employee;
import com.example.demo.repository.ManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PreFilterTests extends TestUsersContext {

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
    void superVisor_addEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        sut.addEmployees(
                new ArrayList<>(
                        List.of(
                                new Employee("M0003", "S0001", "david"),
                                new Employee("E0003", "M0001", "smith")
                        )
                )
        );


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(new Employee("M0003", "S0001", "david")));
        assertFalse(result.containsValue(new Employee("E0003", "M0001", "smith")));
        verify(repository, times(1)).addEmployees(eq(
                List.of(
                        new Employee("M0003", "S0001", "david")
                )
        ));
    }

    @Test
    void junhyunny_addEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        sut.addEmployees(
                new ArrayList<>(
                        List.of(
                                new Employee("M0003", "S0001", "david"),
                                new Employee("E0003", "M0001", "smith")
                        )
                )
        );


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(new Employee("E0003", "M0001", "smith")));
        assertFalse(result.containsValue(new Employee("M0003", "S0001", "david")));
        verify(repository, times(1)).addEmployees(eq(
                List.of(
                        new Employee("E0003", "M0001", "smith")
                )
        ));
    }

    @Test
    void tangerine_addEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        sut.addEmployees(
                new ArrayList<>(
                        List.of(
                                new Employee("M0003", "S0001", "david"),
                                new Employee("E0003", "M0002", "smith")
                        )
                )
        );


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(new Employee("E0003", "M0002", "smith")));
        assertFalse(result.containsValue(new Employee("M0003", "S0001", "david")));
        verify(repository, times(1)).addEmployees(eq(
                List.of(
                        new Employee("E0003", "M0002", "smith")
                )
        ));
    }

    @Test
    void jua_addEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(
                        new ArrayList<>(
                                List.of(
                                        new Employee("E0003", "E0001", "smith")
                                )
                        )
                )
        );
        verify(repository, times(0)).addEmployees(any());
    }

    @Test
    void tory_addEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(
                        new ArrayList<>(
                                List.of(
                                        new Employee("E0003", "E0002", "smith")
                                )
                        )
                )
        );
        verify(repository, times(0)).addEmployees(any());
    }
}
