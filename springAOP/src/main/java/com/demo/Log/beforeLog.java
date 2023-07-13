package com.demo.Log;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("beforeLog")
// Before 前置增强
public class beforeLog implements MethodBeforeAdvice {
    //method : 要执行的目标对象的方法
    //objects : 被调用的方法的参数
    //Object : 目标对象
    @Override
    public void before(Method method, Object[] args, Object target) {
        assert target != null;
        System.out.println(target.getClass().getName() + "的" + method.getName() + "方法被执行了");
    }
}
