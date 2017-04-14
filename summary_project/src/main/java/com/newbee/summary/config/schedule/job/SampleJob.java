package com.newbee.summary.config.schedule.job;

import com.newbee.summary.config.schedule.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务运行工厂类
 */
@DisallowConcurrentExecution
public class SampleJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(SampleJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug(">>>>>>>>>>>>>>>>>任务一成功运行");
    }
}
