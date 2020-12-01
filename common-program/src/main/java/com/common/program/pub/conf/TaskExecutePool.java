package com.common.program.pub.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 连接池配置
 */
@Configuration  
@EnableAsync  
public class TaskExecutePool {
    private static final Logger log = LoggerFactory.getLogger(TaskExecutePool.class);
    @Bean("myTaskAsyncPool")
    public Executor myTaskAsyncPool() {
        log.info("start TaskExecutePool myTaskAsyncPool");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置核心线程数
        executor.setMaxPoolSize(20);
        //配置队列容量
        executor.setQueueCapacity(1000);
        //设置线程活跃时间
        executor.setKeepAliveSeconds(60);
        //设置线程名
        executor.setThreadNamePrefix("myTaskAsyn-");
        //设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
        executor.initialize();  
        return executor;  
    }  
}
