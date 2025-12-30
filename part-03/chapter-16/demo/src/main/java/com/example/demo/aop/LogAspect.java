package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut( // 1
            "execution(* com.example.demo.service.OrderService.createOrder(..))"  // 2
    )
    private void executionCreateProduct() {
    }

    @Around("executionCreateProduct()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("begin create order");
        Object result = joinPoint.proceed();
        System.out.println("end create order");
        return result;
    }
}
