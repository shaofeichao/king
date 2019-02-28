package com.sfc.mq.rabbitmq_server.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听队列
 */
@Component
@RabbitListener(queues = "okong")
public class ConsumerController {
    /**
     * @RabbitHandler 指定消息的处理方法
     * @param message
     */
    @RabbitHandler
    public void process(String message) {
        System.out.println("接收的消息为:"+message);
    }
}
