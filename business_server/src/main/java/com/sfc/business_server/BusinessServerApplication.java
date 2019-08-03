package com.sfc.business_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BusinessServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessServerApplication.class, args);
    }

}

