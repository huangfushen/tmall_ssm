package com.tmall.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tmall.pojo.*;
import com.tmall.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.mapper.RoleMapper;
import com.tmall.mapper.UserRoleMapper;
import com.tmall.service.RoleService;
import com.tmall.service.UserService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    AdministratorService administratorService;

    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        Administrator administrator = administratorService.getByName(userName);
        if (null == administrator)
            return roles;

        roles = listRoles(administrator);
        return roles;
    }

    @Override
    public void add(Role u) {
        roleMapper.insert(u);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);

    }

    @Override
    public List<Role> listRoles(Administrator administrator) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();

        example.createCriteria().andUidEqualTo(administrator.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);

        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }

}