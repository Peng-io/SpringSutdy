package com.demo.AppConfig;

import com.demo.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //代表这是一个配置类
@Import(AppConfig2.class)  //导入合并其他配置类，类似于配置文件中的 include 标签
public class AppConfig {

    @Bean("user") // <bean id="user"/> 通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id！也可在 @Bean中声明
    public User getUser() {
        return new User();
    }
}
