package com.sfc.mq.kafka_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单示例 发送和接收队列消息
 * @author oKong
 */
@RestController
@RequestMapping("/sendKafka")
public class SendController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(String msg) {
        System.out.println(">>>>>>" + msg);
        String message = "{\"order_no\":\"5054264322864215498\"}";
        kafkaTemplate.send("test", message);
        return "已发送";
    }
}
