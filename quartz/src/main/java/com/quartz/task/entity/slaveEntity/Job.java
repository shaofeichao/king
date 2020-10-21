package com.quartz.task.entity.slaveEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {
    private String job_id;
    private String job_name;
    private String job_group;
    private String job_class;
    private String job_cron_exprssion;
    private String job_status;
    private String job_update_time;
}
