package com.example.demo.service;

import com.example.demo.TestUsersContext;
import com.example.demo.domain.Employee;
import com.example.demo.repository.ManagementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PostFilterTests extends TestUsersContext {

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
    void supervisor_getEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(supervisorAuthentication));


        var result = sut.getMyEmployees();


        assertEquals(3, result.size());
        assertEquals(new Employee("S0001", "S0001", "supervisor"), result.get("S0001"));
        assertEquals(new Employee("M0001", "S0001", "junhyunny"), result.get("M0001"));
        assertEquals(new Employee("M0002", "S0001", "tangerine"), result.get("M0002"));
        verify(repository, times(1)).getAllEmployees();
    }

    @Test
    void junhyunny_getEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(junhyunnyAuthentication));


        var result = sut.getMyEmployees();


        assertEquals(2, result.size());
        assertEquals(new Employee("M0001", "S0001", "junhyunny"), result.get("M0001"));
        assertEquals(new Employee("E0001", "M0001", "jua"), result.get("E0001"));
        verify(repository, times(1)).getAllEmployees();
    }

    @Test
    void tangerine_getEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(tangerineAuthentication));


        var result = sut.getMyEmployees();


        assertEquals(2, result.size());
        assertEquals(new Employee("M0002", "S0001", "tangerine"), result.get("M0002"));
        assertEquals(new Employee("E0002", "M0002", "tory"), result.get("E0002"));
        verify(repository, times(1)).getAllEmployees();
    }

    @Test
    void jua_getEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(juaAuthentication));


        var result = sut.getMyEmployees();


        assertEquals(1, result.size());
        assertEquals(new Employee("E0001", "M0001", "jua"), result.get("E0001"));
        verify(repository, times(1)).getAllEmployees();
    }

    @Test
    void tory_getEmployees_success() {
        SecurityContextHolder.setContext(new SecurityContextImpl(toryAuthentication));


        var result = sut.getMyEmployees();


        assertEquals(1, result.size());
        assertEquals(new Employee("E0002", "M0002", "tory"), result.get("E0002"));
        verify(repository, times(1)).getAllEmployees();
    }
}
