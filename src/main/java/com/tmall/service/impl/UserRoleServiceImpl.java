package com.tmall.service.impl;

import java.util.List;


import com.tmall.pojo.Administrator;
import com.tmall.pojo.UserRole;
import com.tmall.pojo.UserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.mapper.UserRoleMapper;
import com.tmall.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public void setRoles(Administrator administrator, long[] roleIds) {
        //删除当前用户所有的角色
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(administrator.getId());
        List<UserRole> urs = userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs)
            userRoleMapper.deleteByPrimaryKey(userRole.getId());

        //设置新的角色关系
        if (null != roleIds)
            for (long rid : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRid(rid);
                userRole.setUid(administrator.getId());
                userRoleMapper.insert(userRole);
            }
    }

    @Override
    public void deleteByUser(long userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserRole> urs = userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }

    @Override
    public void deleteByRole(long roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<UserRole> urs = userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }

}