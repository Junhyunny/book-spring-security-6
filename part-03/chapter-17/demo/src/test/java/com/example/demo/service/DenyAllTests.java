package com.example.demo.service;

import com.example.demo.TestUsersContext;
import com.example.demo.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DenyAllTests extends TestUsersContext {

    @Autowired
    V1ManagementService sut;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void supervisor_denyAll() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getMyEmployees()
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("S0001")
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(new Employee("E0003", "M0001", "david"))
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(List.of(new Employee("E0003", "M0001", "david")))
        );
    }

    @Test
    void junhyunny_denyAll() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getMyEmployees()
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("S0001")
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(new Employee("E0003", "M0001", "david"))
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(List.of(new Employee("E0003", "M0001", "david")))
        );
    }

    @Test
    void tangerine_denyAll() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getMyEmployees()
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("S0001")
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(new Employee("E0003", "M0001", "david"))
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(List.of(new Employee("E0003", "M0001", "david")))
        );
    }

    @Test
    void jua_denyAll() {
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getMyEmployees()
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("S0001")
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(new Employee("E0003", "M0001", "david"))
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(List.of(new Employee("E0003", "M0001", "david")))
        );
    }

    @Test
    void tory_denyAll() {
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getMyEmployees()
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.getEmployeeById("S0001")
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(new Employee("E0003", "M0001", "david"))
        );
        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployees(List.of(new Employee("E0003", "M0001", "david")))
        );
    }
}
