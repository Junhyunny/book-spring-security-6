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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PreAuthorizeTests extends TestUsersContext {

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
    void superVisorMyEmployee_addEmployee_success() {
        var newEmployee = new Employee("M0003", "S0001", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        sut.addEmployee(newEmployee);


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(newEmployee));
        verify(repository, times(1)).addEmployee(any());
    }

    @Test
    void superVisorOtherManagersEmployee_addEmployee_throwAuthorizationDeniedException() {
        var newEmployee = new Employee("M0003", "S0002", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(newEmployee)
        );
        verify(repository, times(0)).addEmployee(any());
    }

    @Test
    void junhyunnyMyEmployee_addEmployee_success() {
        var newEmployee = new Employee("E0003", "M0001", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        sut.addEmployee(newEmployee);


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(newEmployee));
        verify(repository, times(1)).addEmployee(any());
    }

    @Test
    void junhyunnyOtherManagersEmployee_addEmployee_throwAuthorizationDeniedException() {
        var newEmployee = new Employee("E0003", "M0002", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(newEmployee)
        );
        verify(repository, times(0)).addEmployee(any());
    }

    @Test
    void tangerineMyEmployee_addEmployee_success() {
        var newEmployee = new Employee("E0003", "M0002", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        sut.addEmployee(newEmployee);


        var result = repository.getAllEmployees();
        assertTrue(result.containsValue(newEmployee));
        verify(repository, times(1)).addEmployee(any());
    }

    @Test
    void tangerineOtherManagersEmployee_addEmployee_throwAuthorizationDeniedException() {
        var newEmployee = new Employee("E0003", "M0001", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(newEmployee)
        );
        verify(repository, times(0)).addEmployee(any());
    }

    @Test
    void jua_addEmployee_throwAuthorizationDeniedException() {
        var newEmployee = new Employee("E0003", "E0001", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(newEmployee)
        );
        verify(repository, times(0)).addEmployee(any());
    }

    @Test
    void tory_addEmployee_throwAuthorizationDeniedException() {
        var newEmployee = new Employee("E0003", "E0002", "david");
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        assertThrows(
                AuthorizationDeniedException.class,
                () -> sut.addEmployee(newEmployee)
        );
        verify(repository, times(0)).addEmployee(any());
    }
}
