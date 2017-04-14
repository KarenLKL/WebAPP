package com.newbee.summary.config.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kl09 on 2017/4/11.
 */
@DisallowConcurrentExecution
public class ProdectLineStatusQueryJob implements Job {
    private static Logger logger= LoggerFactory.getLogger(ProdectLineStatusQueryJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>查询生产线状态");
    }
}
