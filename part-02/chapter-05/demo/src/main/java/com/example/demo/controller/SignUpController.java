package com.example.demo.controller;

import com.example.demo.domain.model.SignUp;
import com.example.demo.service.SignUpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private final SignUpService signupService;

    public SignUpController(SignUpService signupService) {
        this.signupService = signupService;
    }

    /* [1] 회원 가입 페이지를 반환 */
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /* [2] 회원 가입 요청 처리 */
    @PostMapping("/signup")
    public String signup(SignUp requestBody) {
        signupService.signup(requestBody);
        return "redirect:/login";
    }
}