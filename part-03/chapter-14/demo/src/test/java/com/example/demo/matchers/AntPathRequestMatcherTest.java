package com.example.demo.matchers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AntPathRequestMatcherTest {

    @ParameterizedTest
    @CsvSource(value = {
            "/com/test,true",
            "/com/tast,true",
            "/com//test,true",
            "/com/t?st,true",
            "/com/tst,false",
            "/com/t/st,false",
            "/com/test/,false",
            "/com/test.html,false",
            "/com/task,false",
    }, delimiterString = ",")
    void question_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/t?st");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/ple,true",
            "/com/apple,true",
            "/com//apple,true",
            "/com/apple/,false",
            "/com/apple.html,false",
    }, delimiterString = ",")
    void first_single_asterisk_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/*ple");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/apple,true",
            "/com/apple.html,true",
            "/com/apple/,false",
            "/com/red/apple,false",
    }, delimiterString = ",")
    void second_single_asterisk_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/*");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/red/apple,true",
            "/com/red/blue/apple,false",
    }, delimiterString = ",")
    void third_single_asterisk_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/*/apple");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/apple,true",
            "/com/apple/,true",
            "/com/red/apple,true",
            "/com/red/apple.html,true",
    }, delimiterString = ",")
    void first_double_asterisk_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/**");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/apple,true",
            "/com/red/apple,true",
            "/com/red/blue/apple,true",
            "/com/red/APPLE,false",
            "/com/red/appl,false",
            "/com/red/apple/,false",

    }, delimiterString = ",")
    void second_double_asterisk_mark_pattern(String urlPath, boolean expectedResult) {
        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setServletPath(urlPath);


        var sut = new AntPathRequestMatcher("/com/**/apple");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }
}
