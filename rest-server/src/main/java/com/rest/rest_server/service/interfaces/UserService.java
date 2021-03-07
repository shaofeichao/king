package com.rest.rest_server.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.rest.rest_server.entity.User;

public interface UserService {
    public String selectUser();
    public PageInfo<User> selectUserList(int currentPage,int pageSize);
    public int insertUser(User user) throws RuntimeException;
}
