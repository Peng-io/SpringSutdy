package com.demo.Dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDao {
    @Value("地瓜")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "name='" + name + '\'' +
                '}';
    }

}
