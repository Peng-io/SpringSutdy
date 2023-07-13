package com.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Student {
    public void hello(String hello) {
        System.out.println(hello);
    }
}
