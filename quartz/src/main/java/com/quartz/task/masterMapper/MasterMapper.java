package com.quartz.task.masterMapper;

import com.quartz.task.entity.masterEntity.test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MasterMapper {
    test getTest();
}