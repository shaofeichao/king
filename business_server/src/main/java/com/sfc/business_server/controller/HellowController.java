package com.sfc.business_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowController {
    @RequestMapping("/hellow")
    public String index(){
        return "business spring boot!";
    }
}
