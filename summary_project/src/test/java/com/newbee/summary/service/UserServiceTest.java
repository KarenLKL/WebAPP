package com.newbee.summary.service;

import com.newbee.summary.SummaryApplication;
import com.newbee.summary.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by kl09 on 2017/4/5.
 */
@SpringBootTest(classes = {SummaryApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void queryTest(){
        List<SysUser> users = userService.query();
        Assert.assertTrue(users.size()>0);
    }

}
