package com.sfc.sso_server.dao.interfaces;

import com.sfc.sso_server.entity.t_group;

public interface t_groupMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(t_group record);

    int insertSelective(t_group record);

    t_group selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(t_group record);

    int updateByPrimaryKey(t_group record);


}