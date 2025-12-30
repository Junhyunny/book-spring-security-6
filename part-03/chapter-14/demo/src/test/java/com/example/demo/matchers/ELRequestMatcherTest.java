package com.example.demo.matchers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.util.matcher.ELRequestMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ELRequestMatcherTest {

    @ParameterizedTest
    @CsvSource(value = {
            "192.168.0.1,192.168.0.1,true",
            "192.168.0.1,192.168.0.2,false",
            "192.168.0.0/24,192.168.0.2,true",
            "192.168.0.0/24,192.168.0.255,true",
            "192.168.0.0/24,192.168.1.0,false",
    }, delimiterString = ",")
    void hasIpAddress(String ip, String requestIp, boolean expectedResult) {

        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRemoteAddr(requestIp);


        var sut = new ELRequestMatcher(String.format("hasIpAddress('%s')", ip));


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }
}
