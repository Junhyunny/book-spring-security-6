package com.example.demo.domain.model;

import com.example.demo.domain.entity.UserEntity;

import java.util.function.UnaryOperator;

public record SignUp(
        String username,
        String password
) {
    /* [1] 메서드 참조를 통한 메서드 객체 파라미터 전달 */
    public UserEntity toEntity(UnaryOperator<String> encode) {
        /* [2] 비밀번호를 암호화 */
        return new UserEntity(username, encode.apply(password));
    }
}