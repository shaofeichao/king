package com.rest.rest_server.dao.interfaces;

import com.rest.rest_server.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    List<User> selectUserList();
}