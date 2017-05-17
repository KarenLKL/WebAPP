package com.newbee.summary.util.flatmap;

import com.newbee.summary.SummaryApplication;
import com.newbee.summary.util.lambda.flatmap.Role;
import com.newbee.summary.util.lambda.flatmap.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kl09 on 2017/5/12.
 */
@SpringBootTest(classes = {SummaryApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class FlatMapTest {

    @Test
    public void flatMapTest(){
        getUsers().stream().flatMap(u -> u.getRoles())
    }





    public List<User> getUsers() {
        User user = new User();
        user.setAge(10);
        user.setUserName("张一");
        Role r1 = new Role();
        r1.setCode("1111");
        r1.setRoleName("管理员一");
        Role r2 = new Role();
        r1.setCode("2222");
        r1.setRoleName("管理员二");
        Role r3 = new Role();
        r1.setCode("3333");
        r1.setRoleName("操作员");
        user.setRoles(Arrays.asList(r1, r2, r3));

        User u2 = new User();
        user.setAge(20);
        user.setUserName("李四");
        Role r4 = new Role();
        r4.setCode("4444");
        r4.setRoleName("游客一");
        Role r5 = new Role();
        r5.setCode("5555");
        r5.setRoleName("游客二");
        Role r6 = new Role();
        r6.setCode("6666");
        r6.setRoleName("游客三");

        return Arrays.asList(user, u2);
    }
}
