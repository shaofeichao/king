package com.quartz.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        System.out.println("===========================================QuartzApplication start");
        SpringApplication.run(QuartzApplication.class, args);
        System.out.println("===========================================================success");
    }
}
