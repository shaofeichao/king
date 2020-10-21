package com.quartz.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@Slf4j
public class Hellow implements BaseJob {

    public Hellow() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("HelloJob执行时间: {}", new Date());
    }
}
