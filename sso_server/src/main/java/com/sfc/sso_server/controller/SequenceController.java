package com.sfc.sso_server.controller;

import com.sfc.sso_server.dao.interfaces.NodeEechatFansBaseDataMapper;
import com.sfc.sso_server.pub.vo.NodeEechatFansBaseDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sequence")
public class SequenceController {

    @PostMapping("/getSequence")
    public String getSequence(String aa){
        return "aa";
    }
}
