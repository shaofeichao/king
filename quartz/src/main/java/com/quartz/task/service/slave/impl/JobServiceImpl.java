package com.quartz.task.service.slave.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quartz.task.entity.slaveEntity.Job;
import com.quartz.task.service.slave.IJobService;
import com.quartz.task.slaveMapper.SlaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements IJobService {

    @Autowired
    private SlaveMapper slaveMapper;

    public List<Job> InitializerJob() {
        List<Job> list = slaveMapper.InitializerJob();
        return list;
    }

    public List<Job> getJob() {
        List<Job> list = slaveMapper.getJob();
        return list;
    }

    public PageInfo<Job> getJob(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Job> list = slaveMapper.getJob();
        PageInfo<Job> page = new PageInfo<Job>(list);
        return page;
    }
    public void updateJob(String jobId, String jobCronExpression){
        slaveMapper.updateJob(jobId, jobCronExpression);
    }

    public void updateJobStatus(String jobId, String jobStatus){
        slaveMapper.updateJobStatus(jobId, jobStatus);
    }

    public void saveJob(String jobName, String jobClass, String jobGroup, String jobCronExpression){
        Job job = new Job();
        job.setJob_name(jobName);
        job.setJob_class(jobClass);
        job.setJob_group(jobGroup);
        job.setJob_cron_exprssion(jobCronExpression);
        job.setJob_status("1");
        slaveMapper.saveJob(job);
    }
}
