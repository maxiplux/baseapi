package io.api.base.service;

import org.quartz.SchedulerException;

public interface JobServices {


    String scheduleJob(String name,  String cronScheduleExpression) throws SchedulerException;

    boolean deleteJob(String name);
}
