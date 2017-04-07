package com.newbee.summary.controller;

import com.newbee.summary.entity.SysUser;
import com.newbee.summary.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kl09 on 2017/4/7.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public List<SysUser> query(){
        List<SysUser> query = userService.query();
        return query;
    }
}
