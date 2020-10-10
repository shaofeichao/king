package com.example.demo.job;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class HelloJob implements BaseJob {

    public HelloJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("HelloJob执行时间: {}", new Date());
    }
}