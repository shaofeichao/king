package com.sfc.sso_server.controller;

import com.sfc.sso_server.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public String createUser(@RequestParam String username){
        log.info(">>>>>>获取到前台参数{}...",username);
        String respJson = userService.selectUserList();
        return respJson;
    }
}
