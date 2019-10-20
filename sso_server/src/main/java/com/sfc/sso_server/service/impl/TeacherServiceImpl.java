package com.sfc.sso_server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sfc.sso_server.entity.t_teacher;
import com.sfc.sso_server.service.interfaces.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private com.sfc.sso_server.dao.interfaces.t_teacherMapper t_teacherMapper;

    /**
     * 返回JSON
     * @return
     */
    @Override
    public String selectTeacher() {
        System.out.println(">>>>>开始查询...");
        List<t_teacher> list = t_teacherMapper.getTeacherList();
        log.info(">>>>>获取到mysql数据条数:{}条！",list.size());
        String json = JSONObject.toJSONString(list);
        log.info(">>>>>>json is:{}",json);
        return json;
    }
}