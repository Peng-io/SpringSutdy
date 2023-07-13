# spring整合Mybatis

## 导入依赖

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <dependency>
        <!--            mybatis-->
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <!--        mysql-connector-java-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>
    <!--        aspectJ AOP 织入器-->
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
    <!--        mybatis-spring整合包-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>
</dependencies>
```

## 配置Maven静态资源过滤问题！

```xml

<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

# xml形式

## db.properties

```properties
jdbc.url=jdbc:mysql://localhost:3306/studentdb?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8
jdbc.username=root
jdbc.password=root
```

## mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <typeAliases>
        <package name="com.demo.pojo"/>
    </typeAliases>
</configuration>
```

## spring-mybatis.xml

```text
这个配置是基本上是写死的
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath:db.properties"/>
    <!--    配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url"
                  value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <!--    配置SqlSessionFactory，关联MyBatis-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/demo/mapper/UserMapper.xml"/>
    </bean>
    <!--    注册sqlSessionTemplate，关联sqlSessionFactory-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--利用构造器注入-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
</beans>
```

## ApplicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--    导入spring mybatis 配置文件-->
    <import resource="spring-mybatis.xml"/>

    <!--    Controller 注解不扫描 留给MVC-->
    <context:component-scan base-package="com.demo">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
    <!--    开启AOP注解支持-->
    <aop:aspectj-autoproxy/>

    <bean id="userMapper" class="com.demo.Service.UserService">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>
```

```text
也可以使用 MapperScannerConfigurer 动态实现mapper接口并且注入spring容器中
这样就不用配置 SqlSessionTemplate 还可以少些一个mapper实现类
```

```xml
    <!--    MapperScannerConfigurer 创建的代理类实现了 UserMapper 接口,并且注入到spring容器中。-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    <!--    配置扫描Dao接口包，动态实现Dao接口注入到spring容器中-->
    <property name="basePackage" value="com.demo.mapper"/>
</bean>
```

# 配置类形式

## ApplicationContextConfig

```java

@Configuration
@ComponentScan(value = "com.demo.Service")
@Import({MybatisConfig.class, jdbcConfig.class})
public class AppConfig {

}
```

## jdbcConfig

```java

@PropertySource(value = "classpath:db.properties")
public class jdbcConfig {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
```

## db.properties

```properties
jdbc.url=jdbc:mysql://localhost:3306/studentdb?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8
jdbc.username=root
jdbc.password=root
```

```java

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
```

## @MapperScan 注解与 mapperScannerConfigurer类

```text
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.demo.mapper");
        return configurer;
    }
```

```text
@MapperScan("com.demo.mapper")
```

两者的效果都是与xml配置 MapperScannerConfigurer效果一样的，都是扫描包下的mapper接口并且实现注入spring容器中

```xml
    <!--    MapperScannerConfigurer 创建的代理类实现了 UserMapper 接口,并且注入到spring容器中。-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    <!--    配置扫描Dao接口包，动态实现Dao接口注入到spring容器中-->
    <property name="basePackage" value="com.demo.mapper"/>
</bean>
```

```