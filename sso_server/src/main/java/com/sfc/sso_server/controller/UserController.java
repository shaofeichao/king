package com.sfc.sso_server.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sfc.sso_server.entity.User;
import com.sfc.sso_server.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
//@Controller(RestController表示本类下的方法都是直接做内容返回到浏览器Controller用于控制层)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 测试查询数据
     * @param username
     * @return
     */
    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public String selectUser(@RequestParam String username){
        log.info(">>>>>>获取到前台参数{}...",username);
        String respJson = userService.selectUser();
        return respJson;
    }

    /**
     * 测试分页查询数据PageHelper
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/selectUserPageUtil", method = RequestMethod.GET)
    public PageInfo<User> selectUserPageUtil(@RequestParam int currentPage, @RequestParam int pageSize){
        log.info(">>>>>>获取到前台参数当前页:{},页大小:{}",currentPage, pageSize);
        PageInfo<User> userList = userService.selectUserList(currentPage,pageSize);
        return userList;
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

    @RequestMapping(value = "/insertUser", method = RequestMethod.GET)
    public void insertUser() throws Exception {
        log.info(">>>>>>insert start..");
        User user = new User();
        user.setUsername("小明");
        user.setAge(20);
        user.setAddress("上海");
        user.setUserpassword("admin");
        int count = userService.insertUser(user);
    }

    @RequestMapping(value = "/setRedis", method = RequestMethod.GET)
    public void setRedis() throws Exception {
        log.info(">>>>>>setRedis start..");
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
    }
}
