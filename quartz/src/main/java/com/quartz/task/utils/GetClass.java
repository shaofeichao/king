package com.quartz.task.utils;

import com.quartz.task.job.BaseJob;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetClass {
    public static BaseJob getClass(String classname) throws Exception {
        try {
            Class<?> class1 = Class.forName(classname);
            return (BaseJob) class1.newInstance();
        } catch (Exception e) {
            log.error("%%%找不到反射类!");
            throw new Exception("找不到反射类!");
        }
    }
}
