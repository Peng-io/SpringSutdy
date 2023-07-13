package com.demo.Service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public List<User> selectUser() {
        return userMapper.selectUser();
    }
}
