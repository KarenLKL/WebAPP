package com.newbee.summary.config.schedule;

import com.newbee.summary.config.schedule.job.DataSynchronizationJob;
import com.newbee.summary.config.schedule.job.ProdectLineStatusQueryJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kl09 on 2017/4/10.
 */
@RestController
@RequestMapping("/quartz")
public class QuartzTest {

    @Autowired
    private CustomJobFactory customJobFactory;

    @GetMapping
    public void quartz(String[] args) {
        try {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001");
            job.setJobName("line_status_polling");
            job.setJobGroup("dataWork");
            job.setJobStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("生产线温湿度状态轮询");
            customJobFactory.addJob(job, ProdectLineStatusQueryJob.class);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/add")
    public void addJob(String[] args) {
        try {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10002");
            job.setJobName("data_synchronization");
            job.setJobGroup("dataWork");
            job.setJobStatus("1");
            job.setCronExpression("0/20 * * * * ?");
            job.setDesc("数据同步任务");
            customJobFactory.addJob(job, DataSynchronizationJob.class);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
