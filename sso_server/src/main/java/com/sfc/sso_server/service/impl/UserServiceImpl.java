package com.sfc.sso_server.service.impl;

import com.sfc.sso_server.dao.interfaces.UserMapper;
import com.sfc.sso_server.entity.User;
import com.sfc.sso_server.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void selectUserList() {
        System.out.println(">>>>>开始查询...");
        List<User> list = userMapper.selectUserList();
        System.out.println(">>>>>开始查询结束！"+list.size());
    }
}
