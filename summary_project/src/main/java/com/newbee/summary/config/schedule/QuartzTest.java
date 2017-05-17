package com.newbee.summary.config.schedule;

import com.alibaba.fastjson.JSONObject;
import com.newbee.summary.config.schedule.job.DataSynchronizationJob;
import com.newbee.summary.config.schedule.job.ProdectLineStatusQueryJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
            jsonObject2Bean();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void jsonObject2Bean(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("userName","张三");
        /*SysUser sysUser = JSONObject.toJavaObject(jsonObject, SysUser.class);
        System.out.println(sysUser.toString());*/

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getForEntity();
    }
}
