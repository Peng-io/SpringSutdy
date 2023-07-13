package com.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//java配置类启用AOP注解
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("com.demo")
public class AopConfig {

}
