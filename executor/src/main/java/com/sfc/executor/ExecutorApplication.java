package com.sfc.executor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sfc.executor.dao.interfaces")
public class ExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
        System.out.println("==============================success==============================");
    }

}
