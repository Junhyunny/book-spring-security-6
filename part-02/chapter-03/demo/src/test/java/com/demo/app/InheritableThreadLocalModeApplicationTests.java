package com.demo.app;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InheritableThreadLocalModeApplicationTests {

    @Test
    void security_context_is_same_in_inheritable_thread_local_mode() throws InterruptedException {
        // 1. 준비
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        var testingToken = new TestingAuthenticationToken("Junhyunny", "12345", "ROLE_USER");
        var securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(testingToken);
        SecurityContextHolder.setContext(securityContext);


        // 2. 실행
        var contextArray = new SecurityContext[1];
        var thread = new Thread(() -> {
            var context = SecurityContextHolder.getContext();
            contextArray[0] = context;
        });
        thread.start();
        thread.join();


        // 3. 검증
        var result = SecurityContextHolder.getContext();
        assertEquals(securityContext, result);
        assertEquals(testingToken, result.getAuthentication());
        assertEquals(securityContext, contextArray[0]);
        assertEquals(testingToken, contextArray[0].getAuthentication());
    }

    @Test
    void security_context_is_null_when_completable_future_in_inheritable_thread_local_mode() {
        // 1. 준비
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        var testingToken = new TestingAuthenticationToken("Junhyunny", "12345", "ROLE_USER");
        var securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(testingToken);
        SecurityContextHolder.setContext(securityContext);


        // 2. 실행
        var contextArray = new SecurityContext[1];
        var completableFuture = CompletableFuture.runAsync(() -> {
            var context = SecurityContextHolder.getContext();
            contextArray[0] = context;
        });
        completableFuture.join();


        // 3. 검증
        var result = SecurityContextHolder.getContext();
        assertEquals(securityContext, result);
        assertEquals(testingToken, result.getAuthentication());
        assertNull(contextArray[0].getAuthentication());
    }
}
