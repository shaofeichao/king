package com.sfc.sso_server.controller;

import com.sfc.sso_server.dao.interfaces.NodeEechatFansBaseDataMapper;
import com.sfc.sso_server.pub.vo.NodeEechatFansBaseDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/fans")
public class FansController {

    @Autowired
    private NodeEechatFansBaseDataMapper nodeEechatFansBaseDataMapper;

    @PostMapping("/saveForUpdate")
    public String selectUser(@RequestBody NodeEechatFansBaseDataVo nodeEechatFansBaseDataVo){
        log.info(">>>>>>获取到前台参数{}...",nodeEechatFansBaseDataVo.toString());
    return "aa";
    }
}
