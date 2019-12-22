package com.tmall.service;
 
import com.tmall.pojo.Administrator;
 
public interface UserRoleService {
 
    public void setRoles(Administrator administrator, long[] roleIds);
    public void deleteByUser(long userId);
    public void deleteByRole(long roleId);
     
}