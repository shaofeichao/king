package com.quartz.task.config;

import com.quartz.task.entity.slaveEntity.Job;
import com.quartz.task.service.slave.IJobService;
import com.quartz.task.utils.GetClass;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class Initializer {

    @Autowired
    private IJobService iJobService;

    @PostConstruct
    public void loadTask() throws Exception {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            log.info("............quartz loadJob start.............");
            List<Job> listJob = iJobService.InitializerJob();
            log.info(".............listTsk size is {}.............", listJob.size());
            for (Job job : listJob) {
                log.info("初始化任务jobname:{}.jobgroup:{}.jobclass:{}.jobcron:{}.", job.getJob_name(), job.getJob_group(), job.getJob_class(), job.getJob_cron_exprssion());
                //构建job信息
                JobDetail jobDetail = JobBuilder.newJob(GetClass.getClass(job.getJob_class()).getClass()).withIdentity(job.getJob_class(), job.getJob_group()).build();
                //表达式调度构建器(即任务执行的时间)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJob_cron_exprssion());
                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJob_class(), job.getJob_group()).withSchedule(scheduleBuilder).withDescription(job.getJob_name()).build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            log.error("%%%Initializer loadJob error is ", e);
            throw new Exception("项目加载定时任务异常导致终止!");
        }
    }
}
