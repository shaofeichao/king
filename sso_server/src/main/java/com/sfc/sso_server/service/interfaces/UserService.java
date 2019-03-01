package com.sfc.sso_server.service.interfaces;

import com.github.pagehelper.PageInfo;
import com.sfc.sso_server.entity.User;

import java.util.List;

public interface UserService {
    public String selectUser();
    public PageInfo<User> selectUserList(int currentPage,int pageSize);
}
