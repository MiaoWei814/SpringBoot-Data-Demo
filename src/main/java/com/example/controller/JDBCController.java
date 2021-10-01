package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: springBoot-data
 * @description:
 * @author: MiaoWei
 * @create: 2021-09-28 16:20
 **/
@RestController
public class JDBCController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/query")
    public List<Map<String,Object>> queryList(){
        String sql = "select * from user";
        //查询返回一个list
        List<Map<String, Object>> query = jdbcTemplate.queryForList(sql);
        return query;
    }

    @RequestMapping("/addUser")
    public String addUser(){
        String sql = "insert into user(id,name,pwd) values (2,'小威','2222')";
        jdbcTemplate.update(sql);
        return "ok";
    }
}
