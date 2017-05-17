package com.newbee.summary.util.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kl09 on 2017/4/24.
 */
public class CollectioneDemo {

    private static Logger logger = LoggerFactory.getLogger(CollectioneDemo.class);

    public static void main(String[] args) {
        getUserName();
    }

    public static List<User> getUserList() {
        User user1 = new User();
        user1.setUserName("zhangsan");
        user1.setAddress("五指山");
        user1.setAge(23);
        User user2 = new User();
        user2.setUserName("lisi");
        user2.setAddress("五指山");
        user2.setAge(10000);
        User user3 = new User();
        user3.setUserName("wangwu");
        user3.setAddress("五顶山");
        user3.setAge(99);
        List<User> list = Arrays.asList(user1, user2, user3);
        return list;
    }

    private static void getUserName() {

        long countBtyName = getUserList().stream().filter(user -> user.getUserName().equals("张三")).count();
        System.out.println(countBtyName);

    }


    public static class User {
        private String userName;
        private String address;
        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", address='" + address + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
