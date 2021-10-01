package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springBoot-data
 * @description:
 * @author: MiaoWei
 * @create: 2021-09-30 22:14
 **/
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public List<User> queryList(){
        List<User> users = userMapper.queryList();
        return users;
    }
}
