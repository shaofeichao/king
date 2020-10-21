package com.quartz.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

@Slf4j
public class Hi implements BaseJob {

    public Hi() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("Hi执行时间: {}", new Date());
    }
}
