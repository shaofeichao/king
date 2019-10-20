package com.sfc.sso_server.controller;

import com.sfc.sso_server.service.interfaces.TeacherService;
import com.sfc.sso_server.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@Controller(RestController表示本类下的方法都是直接做内容返回到浏览器Controller用于控制层)
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private com.sfc.sso_server.service.interfaces.TeacherService TeacherService;

    @RequestMapping(value = "/selectTeacher", method = RequestMethod.GET)
    public String selectUser(@RequestParam String username){
        log.info(">>>>>>获取到前台参数{}...",username);
        String respJson = TeacherService.selectTeacher();
        return respJson;
    }
}
