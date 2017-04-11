package com.newbee.summary.config.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 同步远程数据
 * Created by kl09 on 2017/4/11.
 */
@DisallowConcurrentExecution
public class DataSynchronizationJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("--------------同步远程数据-------------------");
    }
}
