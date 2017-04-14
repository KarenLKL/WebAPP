package com.newbee.summary.config.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步远程数据
 * Created by kl09 on 2017/4/11.
 */
@DisallowConcurrentExecution
public class DataSynchronizationJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(DataSynchronizationJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("--------------同步远程数据-------------------");
    }
}
