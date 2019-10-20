package com.sfc.sso_server.dao.interfaces;

import com.sfc.sso_server.entity.t_teacher;

import java.util.List;

public interface t_teacherMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(t_teacher record);

    int insertSelective(t_teacher record);

    t_teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(t_teacher record);

    int updateByPrimaryKey(t_teacher record);

    List<t_teacher> getTeacherList();
}