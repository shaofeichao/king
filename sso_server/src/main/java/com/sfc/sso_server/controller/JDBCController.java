package com.sfc.sso_server.controller;

import com.sfc.sso_server.entity.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUsers")
    public void getDbType(){
        String sql = "select * from user";
        List<users> users =  jdbcTemplate.query(sql,new BeanPropertyRowMapper(users.class));
        System.out.print(users.size());
    }
}
