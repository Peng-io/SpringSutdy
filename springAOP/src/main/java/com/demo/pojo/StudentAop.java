package com.demo.pojo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class StudentAop {
    @Before("execution(* com.demo.pojo.Student.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("目标对象：" + joinPoint.getTarget());
        System.out.println("被调用的方法的参数：" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("方法执行前");
    }

    @After("execution(* com.demo.pojo.Student.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("方法执行后");
    }
}
