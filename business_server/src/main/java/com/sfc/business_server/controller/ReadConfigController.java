package com.sfc.business_server.controller;

import com.sfc.business_server.bean.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class ReadConfigController {
    @Autowired
    ConfigBean configBean;

    @RequestMapping(value = "/business")
    public String business(){
        return configBean.getName()+":"+configBean.getAge()+":"+configBean.getUuid();
    }
}
