package com.sfc.mq.kafka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class KafkaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServerApplication.class, args);
    }

}
