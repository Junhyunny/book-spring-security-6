package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

@Controller
public class LoginController {

    private final static String DEFAULT_ERROR_QUERY_PARAM = "fail";
    private final static String DEFAULT_ERROR_MESSAGE = "사용자 정보가 유효하지 않습니다.";

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest servletRequest) {
        var modelAndView = new ModelAndView("login");
        modelAndView.addObject("errorMessage", errorMessage(servletRequest));
        return modelAndView;
    }

    private String errorMessage(HttpServletRequest servletRequest) {
        var session = servletRequest.getSession(false);
        var isFailed = servletRequest.getParameter(DEFAULT_ERROR_QUERY_PARAM) != null;
        if (!isFailed) {
            return null;
        }
        if (session == null
                || !(session.getAttribute(AUTHENTICATION_EXCEPTION) instanceof AuthenticationException exception)
        ) {
            return DEFAULT_ERROR_MESSAGE;
        }
        var errorMessage = exception.getMessage();
        return StringUtils.hasText(errorMessage) ? errorMessage : DEFAULT_ERROR_MESSAGE;
    }
}