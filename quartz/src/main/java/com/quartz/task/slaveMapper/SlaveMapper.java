package com.quartz.task.slaveMapper;

import com.quartz.task.entity.slaveEntity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SlaveMapper {
    List<Job> InitializerJob();
    List<Job> getJob();
    void updateJob(@Param("jobId") String jobId, @Param("jobCronExpression") String jobCronExpression);
    void updateJobStatus(@Param("jobId") String jobId, @Param("jobStatus") String jobStatus);
    void saveJob(Job job);
}
