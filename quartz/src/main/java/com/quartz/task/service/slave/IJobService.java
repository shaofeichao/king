package com.quartz.task.service.slave;

import com.github.pagehelper.PageInfo;
import com.quartz.task.entity.slaveEntity.Job;

import java.util.List;

public interface IJobService {
    List<Job> InitializerJob();
    List<Job> getJob();
    PageInfo<Job> getJob(int pageNum, int pageSize);
    void updateJob(String jobId, String jobCronExpression);
    void updateJobStatus(String jobId, String jobStatus);
    void saveJob(String jobName, String jobClass, String jobGroup, String jobCronExpression);
}
