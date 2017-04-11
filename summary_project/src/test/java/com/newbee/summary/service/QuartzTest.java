package com.newbee.summary.service;

import com.newbee.summary.config.schedule.ScheduleJob;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kl09 on 2017/4/10.
 */
public class QuartzTest extends BaseServciceTest {

    /**
     * 计划任务map
     */
    private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();


    @Test
    public void quartzTest() {
        QuartzTest quartzTest = new QuartzTest();
    }
}
