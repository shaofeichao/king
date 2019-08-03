package com.sfc.mq.rabbitmq_server.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单示例 发送和接收队列消息
 * @author oKong
 */
@RestController
@RequestMapping("/sendMQ")
public class SendController {
    //AmqpTemplate接口定义了发送和接收消息的基本操作,目前spring官方也只集成了Rabbitmq一个消息队列。。
    @Autowired
    AmqpTemplate rabbitmqTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(String msg) {
        System.out.println(">>>>>>" + msg);
        //发送消息
        for(int i = 1;i<1000;i++) {
            String message = i+"-"+msg;
            rabbitmqTemplate.convertAndSend("okong", message);
        }
        return "消息：" + msg + ",已发送";
    }
}
