package com.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = "com.demo.Service")
@Import({MybatisConfig.class, jdbcConfig.class})
public class AppConfig {

}
