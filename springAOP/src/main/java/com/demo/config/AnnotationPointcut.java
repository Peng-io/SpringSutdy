package com.demo.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AnnotationPointcut {
    @Before("execution(* com.demo.Service.UserService.*(..))")
    public void before() {
        System.out.println("方法执行前");
    }

    @After("execution(* com.demo.Service.UserService.*(..))")
    public void after() {
        System.out.println("方法执行后");
    }
}
