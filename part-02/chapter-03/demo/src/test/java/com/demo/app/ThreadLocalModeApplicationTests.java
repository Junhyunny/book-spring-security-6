package com.demo.app;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ThreadLocalModeApplicationTests {

    @Test
    void security_context_from_other_thread_is_null() throws InterruptedException {
        // 1. 준비
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
        assertNull(contextArray[0].getAuthentication());
    }
}
