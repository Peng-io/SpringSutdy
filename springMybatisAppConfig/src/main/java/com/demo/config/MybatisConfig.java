package com.demo.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

// 自动扫描包里的接口
@MapperScan("com.demo.mapper")
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
        // 加载mybatis配置文件
        sqlSession.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis-config.xml"));
        sqlSession.setDataSource(dataSource);
        return sqlSession;
    }

    
}
