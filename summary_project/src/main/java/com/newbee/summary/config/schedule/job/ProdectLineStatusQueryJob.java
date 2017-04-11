package com.newbee.summary.config.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by kl09 on 2017/4/11.
 */
public class ProdectLineStatusQueryJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("查询生产线状态");
    }
}
