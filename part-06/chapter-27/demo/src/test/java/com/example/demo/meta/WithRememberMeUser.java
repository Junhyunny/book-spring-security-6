package com.example.demo.meta;

import com.example.demo.factory.WithRememberMeUserFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithRememberMeUserFactory.class)
public @interface WithRememberMeUser {

    String username() default "user";

    String[] roles() default {"USER"};
}

