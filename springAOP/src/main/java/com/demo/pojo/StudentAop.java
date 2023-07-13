package com.demo.pojo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAop {
    @Before("execution(* com.demo.pojo.Student.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint);
        System.out.println("方法执行前");
    }

    @After("execution(* com.demo.pojo.Student.*(..))")
    public void after() {
        System.out.println("方法执行后");
    }
}
