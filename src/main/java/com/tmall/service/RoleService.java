package com.tmall.service;
 
import java.util.List;
import java.util.Set;

import com.tmall.pojo.Administrator;
import com.tmall.pojo.Role;
 
public interface RoleService {
    public Set<String> listRoleNames(String userName);
    public List<Role> listRoles(String userName);
    public List<Role> listRoles(Administrator administrator);
 
    public List<Role> list();
    public void add(Role role);
    public void delete(Long id);
    public Role get(Long id);
    public void update(Role role);
     
}