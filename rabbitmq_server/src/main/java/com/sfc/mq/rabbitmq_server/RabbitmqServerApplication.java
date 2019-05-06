package com.sfc.mq.rabbitmq_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RabbitmqServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqServerApplication.class, args);
    }

}
