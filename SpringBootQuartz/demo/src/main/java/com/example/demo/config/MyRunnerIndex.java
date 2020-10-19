package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;

/**
 * @author sfc
 * @date 2020/10/19 17:34
 */
//@Component
public class MyRunnerIndex implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("开始自动加载指定的页面");
        try {
            String url = "http://localhost:8080/etl/v1/JobManager.html";
            Runtime.getRuntime().exec("cmd   /c   start   " + url);//可以指定自己的路径
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
