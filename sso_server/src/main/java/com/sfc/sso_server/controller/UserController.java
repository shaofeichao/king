package com.sfc.sso_server.controller;

import com.sfc.sso_server.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
//@Controller(RestController表示本类下的方法都是直接做内容返回到浏览器Controller用于控制层)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public String selectUser(@RequestParam String username){
        log.info(">>>>>>获取到前台参数{}...",username);
        String respJson = userService.selectUserList();
        return respJson;
    }

    /**
     * 测试静态html跳转类注解使用@Controller
     * 使用Thymeleaf默认去找resources/templates下面的静态文件
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        log.info(">>>>>>获取到前台url");
        return "index";
    }
}
