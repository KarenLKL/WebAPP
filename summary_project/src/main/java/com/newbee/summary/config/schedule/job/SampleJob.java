package com.newbee.summary.config.schedule.job;

import com.newbee.summary.config.schedule.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时任务运行工厂类
 */
@DisallowConcurrentExecution
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        if (scheduleJob != null) {
            if (scheduleJob.getJobName().equals("data_synchronization"))
                System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
        }

    }
}
