package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.JobAndTrigger;
import org.springframework.stereotype.Repository;

@Repository
public interface JobAndTriggerMapper {
    List<JobAndTrigger> getJobAndTriggerDetails();
}
