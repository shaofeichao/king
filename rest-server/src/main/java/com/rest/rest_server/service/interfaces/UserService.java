package com.rest.rest_server.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.rest.rest_server.entity.User;

public interface UserService {
    String selectUser();
    PageInfo<User> selectUserList(int currentPage,int pageSize);
    int insertUser(User user) throws RuntimeException;
}
