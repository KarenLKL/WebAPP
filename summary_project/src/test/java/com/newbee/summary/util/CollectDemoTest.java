package com.newbee.summary.util;

import com.newbee.summary.SummaryApplication;
import com.newbee.summary.util.lambda.CollectDemo;
import com.newbee.summary.util.lambda.CollectioneDemo;
import org.apache.commons.collections.Factory;
import org.assertj.core.internal.Longs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by kl09 on 2017/4/24.
 */
@SpringBootTest(classes = {SummaryApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectDemoTest {
    private static List<CollectioneDemo.User> userList = CollectioneDemo.getUserList();

    @Test
    public void getCollect() {
        List<String> collect = CollectDemo.getCollect();
        Assert.assertEquals(Arrays.asList("a", "b", "c"), collect);
    }

    /**
     * 使用stream操作选取元素形成新的集合
     */
    @Test
    public void mapTest() {
        /*CollectioneDemo.User minAgeuser = userList.stream().min(Comparator.comparing(CollectioneDemo.User::getAge))
                .get();
        System.out.println("年龄最小的同学是：" + minAgeuser.toString());

        CollectioneDemo.User maxAgeuser = userList.stream().max(Comparator.comparing(CollectioneDemo.User::getAge))
                .get();
        System.out.println("年龄最大的同学是：" + maxAgeuser.toString());*/

        userList.stream().mapToInt(CollectioneDemo.User::getAge).forEach(System.out::println);
        CollectioneDemo.User user = userList.stream().max(Comparator.comparingInt(CollectioneDemo.User::getAge)).get();
        System.out.println(user);

    }

    /**
     * reduce操作可以实现从一组值中生成一个值。上述中的count、min、max方法都是reduce操作
     */
    @Test
    public void reduceTestSum() {
        Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(0, (acc, element) -> acc + element);
        System.out.println(reduce);
    }

    /**
     * 利用reduce操作实现min操作
     */
    @Test
    public void reduceTestMin() {
        Integer minNumber = Stream.of(8, 2, 3, 4, 5).reduce((acc, element) -> acc < element ? acc : element).get();
        System.out.println(minNumber);
    }

    @Test
    public void distinctTest() {
        final int[] count = {0};
        Stream.of(1, 2, 3, 4, 54, 6).filter(element -> {
            System.out.println("执行数字>>>" + element);
            count[0]++;
            return element > 4;
        });
    }

    /**
     * 返回字符串列表，包含名字和地址
     */
    @Test
    public void getNameAndAddress() {
        userList.stream().map(user -> new String[]{user.getUserName(), user.getAddress()}).forEach(strings -> System
                .out.println(strings[0]));
    }

    @Test
    public void javaBaseType() {
        IntSummaryStatistics intSummaryStatistics = userList.stream()
                .mapToInt(CollectioneDemo.User::getAge)//得到intStream
                .summaryStatistics();//该方法可以计算各种各样的统计信息，如max、min、avg、sum等等,也可以单独的进行统计
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                intSummaryStatistics.getMax(),
                intSummaryStatistics.getMin(),
                intSummaryStatistics.getAverage(),
                intSummaryStatistics.getSum());
        System.out.println("-------");
    }

    /**
     * 转换成其他集合
     */
    @Test
    public void objectsTest() {
        userList.stream()
                .map(CollectioneDemo.User::getUserName)
                .collect(Collectors.toCollection(TreeSet::new))
                .forEach(System.out::println);
    }

    @Test
    public void transformationCollection() {
        HashSet<CollectioneDemo.User> collect = new HashSet<>(userList);
        collect.forEach(user -> {
            System.out.println(user.getUserName() + "--年龄--" + user.getAge());
        });

        System.out.println(">>>>>>>>>>>>>>升序排序之后<<<<<<<<<<<<<<<<<<<");
        //升序排列
        userList.stream().sorted(Comparator.comparing(CollectioneDemo.User::getAge)).forEach(user -> System.out.println(user.getUserName() + "--年龄--" + user.getAge()));

        //降序排列
        System.out.println(">>>>>>>>>>>>>>降序排序之后<<<<<<<<<<<<<<<<<<<");
        userList.stream().sorted(Comparator.comparing(CollectioneDemo.User::getAge).reversed()).forEach(user -> System.out.println(user.getUserName() + "--年龄--" + user.getAge()));

    }

    /**
     * 分块<br/>
     * 将集合根据年龄40为界限分块
     */
    @Test
    public void getBlockByAge() {
        Map<Boolean, List<CollectioneDemo.User>> collectMap = userList.stream().collect(Collectors.partitioningBy(user
                -> user.getAge() > 40));
        collectMap.get(true).forEach(user -> System.out.println("年龄大于40的" + user.getUserName() + "--年龄--"
                + user.getAge()));
        collectMap.get(false).forEach(user -> System.out.println("年龄小于40的" + user.getUserName() + "--年龄--"
                + user.getAge()));

    }

    /**
     * 分组，同分块不一样，可使用任意数据进行分组
     */
    @Test
    public void groupBy() {
        Map<String, List<CollectioneDemo.User>> collect = userList.stream().collect(Collectors.groupingBy
                (CollectioneDemo.User::getUserName));
        for (Map.Entry<String, List<CollectioneDemo.User>> entry : collect.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    /**
     * 字符串输出（格式）<br/>
     * 输出所有姓名为:[张三,李四,王五]<br/>
     * 允许用户提供分隔符（用以分隔元素）、前缀和后缀。
     */
    @Test
    public void chartsString() {
        String result = userList.stream()
                .map(CollectioneDemo.User::getUserName)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }

    /**
     * 组合收集器<br/>
     * 比如：先分组，然后统计
     */
    @Test
    public void group() {
        userList.stream()
                .collect(Collectors.groupingBy(CollectioneDemo.User::getAddress, Collectors.counting()))
                .forEach((key, count) -> System.out.println(key + "-人数-" + count));


    }

    @Test
    public void streamTest() {
        userList.stream().filter(user -> user.getAge() > 50).forEach(user -> {
            System.out.println(user.getUserName());
        });

        CollectioneDemo.User user = userList.stream().max(Comparator.comparing(CollectioneDemo.User::getAge)).get();
        System.out.println(user);
    }

    @Test
    public void stream() {
        Integer integer = Stream.of(1, 23, 56, 77, 88, 2, 3, 4, 534, 23, 12, 66).reduce((acc, element) -> acc > element ? acc : element)
                .get();
        System.out.println(integer);
    }

}
