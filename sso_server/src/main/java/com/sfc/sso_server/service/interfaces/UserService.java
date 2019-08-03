package com.sfc.sso_server.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.sfc.sso_server.entity.User;

public interface UserService {
    public String selectUser();
    public PageInfo<User> selectUserList(int currentPage,int pageSize);
    public int insertUser(User user) throws RuntimeException;
}
