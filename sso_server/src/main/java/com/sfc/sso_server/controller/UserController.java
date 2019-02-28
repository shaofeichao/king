package com.sfc.sso_server.controller;

import com.sfc.sso_server.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public String createUser(@RequestParam String username){
        System.out.println(">>>>>>"+username);
        userService.selectUserList();
        return "index";
    }
}
