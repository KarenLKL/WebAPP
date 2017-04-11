package com.newbee.summary.config.schedule;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.InitBinder;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.List;

/**
 * Job工厂类
 * Created by kl09 on 2017/4/10.
 */
@Component
public class CustomJobFactory {

    private static Logger logger = LoggerFactory.getLogger(CustomJobFactory.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private static CustomJobFactory customJobFactory;

    public CustomJobFactory() {
    }

    public static CustomJobFactory getInstance() {
        if (customJobFactory == null) {
            customJobFactory = new CustomJobFactory();
            return customJobFactory;
        }
        return customJobFactory;
    }

    /*public void startJob() throws SchedulerException {
        //schedulerFactoryBean 由spring创建注入
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        //这里获取任务信息数据
        List<ScheduleJob> jobList = getAllJob();

        for (ScheduleJob job : jobList) {

            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，创建一个
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

                try {
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
    }*/


    /**
     * 添加动态任务
     *
     * @param job 任务@{@link ScheduleJob}
     * @return boolean
     */
    public boolean addJob(ScheduleJob job,Class c) throws SchedulerException {
        try {
            CronTrigger cronTrigger = getCronTrigger(job);
            JobDetail jobDetail = JobBuilder.newJob(c)
                    .withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());

            //按新的cronExpression表达式构建一个新的trigger
            cronTrigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler().scheduleJob(jobDetail, cronTrigger);
            return true;
        } catch (SchedulerException e) {
            logger.error("添加动态任务失败：{}", e);
            throw e;
        }
    }

    /**
     * 修改任务失败
     *
     * @param job
     * @return
     */
    public boolean updateJob(ScheduleJob job) {
        CronTrigger cronTrigger = null;
        try {
            cronTrigger = getCronTrigger(job);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                    .getCronExpression());

            TriggerKey triggerKey = getTriggerKey(job);
            //按新的cronExpression表达式重新构建trigger
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();

            //按新的trigger重新设置job执行
            scheduler().rescheduleJob(triggerKey, cronTrigger);
            return true;
        } catch (SchedulerException e) {
            logger.error("修改动态任务失败：{}", e);
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 移除job
     *
     * @param job
     * @return
     */
    public boolean removeJob(ScheduleJob job) {
        Scheduler scheduler = scheduler();
        try {
            scheduler.deleteJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
            return true;
        } catch (SchedulerException e) {
            logger.error("移除动态任务失败：{}", e);
            e.printStackTrace();
        }
        return false;
    }

    private Scheduler scheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 获取Trigger，在配置文件中配置的Bean id：sampleJobTrigger
     *
     * @param job
     * @return
     * @throws SchedulerException
     */
    private CronTrigger getCronTrigger(ScheduleJob job) throws SchedulerException {
        TriggerKey triggerKey = getTriggerKey(job);
        return (CronTrigger) scheduler().getTrigger(triggerKey);
    }

    /**
     * 获取TriggerKey
     *
     * @param job 任务
     * @return TriggerKey
     */
    private TriggerKey getTriggerKey(ScheduleJob job) {
        return TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
    }


    public List<ScheduleJob> getAllJob() {
        List<ScheduleJob> list = new ArrayList<ScheduleJob>();
        for (int i = 0; i < 5; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001" + i);
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork" + i);
            job.setJobStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("数据导入任务" + i);
            list.add(job);
        }
        return list;
    }

}
