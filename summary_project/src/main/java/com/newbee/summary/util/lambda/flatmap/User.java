package com.newbee.summary.util.lambda.flatmap;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kl09 on 2017/5/12.
 */
public class User {
    private String userName;
    private int age;
    private List<Role> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
