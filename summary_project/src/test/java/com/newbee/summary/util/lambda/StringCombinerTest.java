package com.newbee.summary.util.lambda;

import com.newbee.summary.SummaryApplication;
import com.newbee.summary.util.lambda.StringCollector.StringCollector;
import com.newbee.summary.util.lambda.StringCollector.StringCombiner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kl09 on 2017/4/27.
 */
@SpringBootTest(classes = {SummaryApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StringCombinerTest {

    private static List<CollectioneDemo.User> userList = CollectioneDemo.getUserList();

    @Test
    public void StringCombinerTest() {
        String result = userList.stream()
                .map(CollectioneDemo.User::getUserName)
                .reduce(new StringCombiner(",", "[", "]"),
                        StringCombiner::add,
                        StringCombiner::merge)
                .toString();
        System.out.println(result);
    }

    /**
     * 自定义字符串收集器
     */
    @Test
    public void StringCollectors() {
        String result = userList.stream()
                .map(CollectioneDemo.User::getUserName)
                .collect(new StringCollector(", ", "[", "]"));
        System.out.println(result);

        HashMap<String, String> cache = new HashMap<>();

    }

    @Test
    public void asListTest() {
        String[] str = new String[]{"a", "b"};
        List<String> list = Arrays.asList(str);

    }

    @Test
    public void dateFormateTest() {
        DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate parse = LocalDate.parse("20170428164712", yyyyMMddHHmmss);
        System.out.println(parse);

    }


}
