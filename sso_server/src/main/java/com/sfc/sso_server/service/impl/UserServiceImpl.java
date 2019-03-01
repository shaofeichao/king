package com.sfc.sso_server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 返回JSON
     * @return
     */
    @Override
    public String selectUser() {
        System.out.println(">>>>>开始查询...");
        List<User> list = userMapper.selectUserList();
        log.info(">>>>>获取到mysql数据条数:{}条！",list.size());
        String json = JSONObject.toJSONString(list);
        log.info(">>>>>>json is:{}",json);
        return json;
    }

    /**
     * 返回list
     * @return
     */
    @Override
    public PageInfo<User> selectUserList(int currentPage,int pageSize) {
        System.out.println(">>>>>开始查询...");
        PageHelper.startPage(currentPage,pageSize);
        List<User> list = userMapper.selectUserList();
        log.info(">>>>>获取到mysql数据条数:{}条！",list.size());
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}