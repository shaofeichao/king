package com.common.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CommonProgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonProgramApplication.class, args);
        System.out.println("==============================success==============================");
    }
}