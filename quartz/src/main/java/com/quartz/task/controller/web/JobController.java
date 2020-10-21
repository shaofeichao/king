package com.quartz.task.controller.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.quartz.task.entity.slaveEntity.Job;
import com.quartz.task.service.slave.IJobService;
import com.quartz.task.utils.GetClass;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private IJobService iJobService;

    /**
     * 数据库中保存的任务列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryJob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        log.info(".........获取在运行的任务列表start..........");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PageInfo<Job> jobAndTrigger = iJobService.getJob(pageNum, pageSize);
            map.put("JobAndTrigger", jobAndTrigger);
            map.put("number", jobAndTrigger.getTotal());
            log.info(".........获取在运行的任务列表end..........");
        } catch (Exception e) {
            log.error("%%%queryJob error is {}!", e);
            throw new Exception("查询任务列表失败!");
        }
        return map;
    }

    /**
     * TODO 这个是在运行的活动列表暂不用
     * 需要调整界面并取消默认分页模式
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/queryRunJob")
    public Map<String, Object> queryRunJob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        log.info(".........获取在运行的任务列表start..........");
        try {
            List<Map<String, Object>> listMap = new ArrayList<>();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    Map<String, Object> mapJob = new HashMap<>();
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(triggers.get(0)));
                    mapJob.put("job_name", triggers.get(0).getDescription());
                    mapJob.put("job_group", groupName);
                    mapJob.put("job_class_name", triggers.get(0).getKey().getName());
                    mapJob.put("job_cron_expression", json.get("cronExpression"));
                    listMap.add(mapJob);
                }
            }
            map.put("JobAndTrigger", listMap);
            map.put("number", listMap.size());
            log.info(".........获取在运行的任务列表end..........");
        } catch (SchedulerException e) {
            log.error("%%%queryJob error is {}!", e);
            throw new Exception("查询任务列表失败请检查后台服务日志!");
        }
        return map;
    }

    /**
     * 新增一个新的任务
     *
     * @throws Exception
     */
    @PostMapping(value = "/addJob")
    public void addjob(@RequestParam(value = "jobName") String jobName,
                       @RequestParam(value = "jobClass") String jobClass,
                       @RequestParam(value = "jobGroup") String jobGroup,
                       @RequestParam(value = "jobCronExpression") String jobCronExpression) throws Exception {
        log.info("创建job任务:jobName:{}.jobClass:{}.jobGroup:{}.jobCronExpression:{}.", jobName, jobClass, jobGroup, jobCronExpression);
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(GetClass.getClass(jobClass).getClass()).withIdentity(jobClass, jobGroup).build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClass, jobGroup).withSchedule(scheduleBuilder).withDescription(jobName).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            log.info("定时任务创建成功");
            iJobService.saveJob(jobName, jobClass, jobGroup, jobCronExpression);
            log.info("持久化数据库完成!");
        } catch (SchedulerException e) {
            log.error("%%%addJob error is {}!", e);
            throw new Exception("新增定时job异常请检查后台服务日志!");
        }
    }

    /**
     * 更新任务
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updateJob")
    public void updateJob(@RequestParam(value = "jobId") String jobId,
                          @RequestParam(value = "jobClass") String jobClass,
                          @RequestParam(value = "jobGroup") String jobGroup,
                          @RequestParam(value = "jobCronExpression") String jobCronExpression) throws Exception {
        try {
            log.info("--------->更新jobid:{}.job:{}.group:{}.cron:{}.", jobId, jobClass, jobGroup, jobCronExpression);
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    String jobClassList = triggers.get(0).getKey().getName();
                    log.info("更新任务获取到列表任务:{}.要更新的任务:{}.", jobClassList, jobClass);
                    if (StringUtils.equals(jobClassList, jobClass)) {
                        TriggerKey triggerKey = TriggerKey.triggerKey(jobClass, groupName);
                        // 表达式调度构建器
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCronExpression);
                        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                        // 按新的cronExpression表达式重新构建trigger
                        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                        // 按新的trigger重新设置job执行
                        scheduler.rescheduleJob(triggerKey, trigger);
                        log.info("更新完成.【jobName】:{}.【下次执行规则】:{}.", jobClass, jobCronExpression);
                    }
                }
            }
            log.info("开始持久化到数据库...");
            iJobService.updateJob(jobId, jobCronExpression);
            log.info("持久化到数据库完成!");
        } catch (SchedulerException e) {
            log.error("%%%updateJob error is {}!", e);
            throw new Exception("更新定时任务失败请检查后台服务日志!");
        }
    }

    /**
     * 暂停任务
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/stopJob")
    public void stopJob(@RequestParam(value = "jobId") String jobId, @RequestParam(value = "jobClass") String jobClass, @RequestParam(value = "jobGroup") String jobGroup) throws Exception {
        try {
            log.info("--------->暂停jobid:{}.job{}.group:{}.", jobId, jobClass, jobGroup);
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    String jobName = triggers.get(0).getJobKey().getName();
                    String jobClassList = triggers.get(0).getKey().getName();
                    log.info("暂停操作获取任务列表:{}.要更新的任务:{}.", jobClassList, jobClass);
                    if (StringUtils.equals(jobClassList, jobClass)) {
                        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
                        log.info("暂停任务:{}完成!", jobClass);
                    }
                }
            }
            log.info("开始持久化到数据库...");
            iJobService.updateJobStatus(jobId, "2");
            log.info("持久化到数据库完成!");
        } catch (SchedulerException e) {
            log.error("%%%stopJob error is {}!", e);
            throw new Exception("暂停任务失败请检查后台服务日志!");
        }
    }

    /**
     * 开始任务
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/startJob")
    public void startJob(@RequestParam(value = "jobId") String jobId, @RequestParam(value = "jobClass") String jobClass, @RequestParam(value = "jobGroup") String jobGroup) throws Exception {
        try {
            log.info("--------->开始jobid:{}.job:{}.group:{}.", jobId, jobClass, jobGroup);
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    String jobName = triggers.get(0).getJobKey().getName();
                    String jobClassList = triggers.get(0).getKey().getName();
                    log.info("开始任务获取当前任务:{}.要开始的任务:{}.", jobClassList, jobClass);
                    if (StringUtils.equals(jobClassList, jobClass)) {
                        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
                        log.info("开始任务:{}完成!", jobClass);
                    }
                }
            }
            log.info("开始持久化到数据库...");
            iJobService.updateJobStatus(jobId, "1");
            log.info("持久化到数据库完成!");
        } catch (SchedulerException e) {
            log.error("%%%startJob error is {}!", e);
            throw new Exception("开始任务失败请检查后台服务日志!");
        }
    }

    /**
     * 删除定时任务
     *
     * @throws Exception
     */
    @PostMapping(value = "/deleteJob")
    public void deletejob(@RequestParam(value = "jobId") String jobId, @RequestParam(value = "jobClass") String jobClass, @RequestParam(value = "jobGroup") String jobGroup) throws Exception {
        try {
            log.info("--------->删除jobid:{}.job:{}.group:{}.", jobId, jobClass, jobGroup);
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    String jobName = triggers.get(0).getJobKey().getName();
                    String jobClassList = triggers.get(0).getKey().getName();
                    log.info("开始任务获取当前任务:{}.要开始的任务:{}.", jobClassList, jobClass);
                    if (StringUtils.equals(jobClassList, jobClass)) {
                        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClass, groupName));
                        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClass, groupName));
                        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
                        log.info("删除任务:{}完成!", jobClass);
                    }
                }
            }
            log.info("开始持久化到数据库...");
            iJobService.updateJobStatus(jobId, "4");
            log.info("持久化到数据库完成!");
        } catch (SchedulerException e) {
            log.error("%%%startJob error is {}!", e);
            throw new Exception("删除任务失败请检查后台服务日志!");
        }
    }
}