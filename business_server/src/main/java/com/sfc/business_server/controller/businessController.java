package com.sfc.business_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class businessController {
    @Value("${business.name}")
    private String name;
    @Value("${business.age}")
    private String age;

    @RequestMapping(value = "/t_business")
    public String business(){
        return name+":"+age;
    }
}
