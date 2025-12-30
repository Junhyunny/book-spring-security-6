package com.example.demo.matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@WebMvcTest
public class MvcRequestMatcherTest {

    @Autowired
    HandlerMappingIntrospector introspector;

    @ParameterizedTest
    @CsvSource(value = {
            "/com/test,true",
            "/com/tast,true",
            "/com/t?st,true",
            "/com//test,false",
            "/com/tst,false",
            "/com/t/st,false",
            "/com/test/,false",
            "/com/test.html,false",
            "/com/task,false",
    }, delimiterString = ",")
    void question_mark_pattern(String urlPath, boolean expectedResult) {

        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRequestURI(urlPath);


        var sut = new MvcRequestMatcher(introspector, "/com/t?st");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/ple,true",
            "/com/apple,true",
            "/com//apple,false",
            "/com/apple/,false",
            "/com/apple.html,false",
    }, delimiterString = ",")
    void single_asterisk_mark_pattern(String urlPath, boolean expectedResult) {

        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRequestURI(urlPath);


        var sut = new MvcRequestMatcher(introspector, "/com/*ple");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "/com/apple,true",
            "/com/apple/,true",
            "/com/red/apple,true",
            "/com/red/apple.html,true",
    }, delimiterString = ",")
    void double_asterisk_mark_pattern_test(String urlPath, boolean expectedResult) {

        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRequestURI(urlPath);


        var sut = new MvcRequestMatcher(introspector, "/com/**");


        assertEquals(expectedResult, sut.matches(httpServletRequest));
    }

    @Test
    void double_asterisk_mark_between_path_throw_exception() {

        var httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setRequestURI("/com/red/green/apple");


        var sut = new MvcRequestMatcher(introspector, "/com/**/apple");


        var throwable = assertThrows(RuntimeException.class, () -> sut.matches(httpServletRequest));
        assertEquals(
                "No more pattern data allowed after {*...} or ** pattern element",
                throwable.getMessage()
        );
    }
}
