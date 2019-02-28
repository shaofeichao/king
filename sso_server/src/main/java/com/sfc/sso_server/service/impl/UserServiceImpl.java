package com.sfc.sso_server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sfc.sso_server.dao.interfaces.UserMapper;
import com.sfc.sso_server.entity.User;
import com.sfc.sso_server.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String selectUserList() {
        System.out.println(">>>>>开始查询...");
        List<User> list = userMapper.selectUserList();
        log.info(">>>>>获取到mysql数据条数:{}条！",list.size());
        String json = JSONObject.toJSONString(list);
        log.info(">>>>>>json is:{}",json);
        return json;
    }
}