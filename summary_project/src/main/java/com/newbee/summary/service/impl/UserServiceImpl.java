package com.newbee.summary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newbee.summary.entity.SysUser;
import com.newbee.summary.entity.SysUserExample;
import com.newbee.summary.mybatis.dao.SysUserMapper;
import com.newbee.summary.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理Service实现
 * Created by kl09 on 2017/4/5.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper userMapper;
    
    @Override
    public List<SysUser> query() {
        SysUserExample sysUserExample = new SysUserExample();
        Page<SysUser> page = PageHelper.startPage(1, 10);
        List<SysUser> users = userMapper.selectByExample(sysUserExample);
        PageInfo<SysUser> result=new PageInfo<>(users);
        return users;
    }
}
