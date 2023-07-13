package com.demo.diyAOP;

import org.springframework.stereotype.Component;

@Component("diy")
public class diyPointcut {
    public  void before(){
        System.out.println("方法执行前");
    }
    public void  after(){
        System.out.println("方法执行后");
    }
}
