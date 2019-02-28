package com.sfc.mq.rabbitmq_server.pub.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置一个名为okong的队列
 */
@Configuration
public class RabbitConfig {
    /**
     * 定义一个名为：oKong 的队列
     * @return
     */
    @Bean
    public Queue okongQueue() {
        return new Queue("okong");
    }
}
