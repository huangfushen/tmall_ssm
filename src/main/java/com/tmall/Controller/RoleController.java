package com.tmall.Controller;
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.tmall.pojo.Permission;
import com.tmall.pojo.Role;
import com.tmall.service.PermissionService;
import com.tmall.service.RolePermissionService;
import com.tmall.service.RoleService;
 
@Controller
@RequestMapping("")
public class RoleController {
    @Autowired RoleService roleService;
    @Autowired RolePermissionService rolePermissionService;
    @Autowired PermissionService permissionService;

    @RequestMapping("role_list")
    public String list(Model model, Page page){
        page.setCount(5);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Role> rs= roleService.list();
        int total = (int) new PageInfo<>(rs).getTotal();
        page.setTotal(total);
        model.addAttribute("rs", rs);
        Map<Role,List<Permission>> role_permissions = new HashMap<>();
        for (Role role : rs) {
            List<Permission> ps = permissionService.list(role);
            role_permissions.put(role, ps);
        }
        model.addAttribute("role_permissions", role_permissions);
        System.out.println(role_permissions);
        return "admin/listRole";
    }

    @RequestMapping("editRolePage")
    public String editRolePage(Model model,long id){
        Role role =roleService.get(id);
        model.addAttribute("role", role);
        List<Permission> ps = permissionService.list();
        model.addAttribute("ps", ps);
        List<Permission> currentPermissions = permissionService.list(role);
        model.addAttribute("currentPermissions", currentPermissions);
        return "admin/editRole";
    }


    @RequestMapping("updateRole")
    public String update(Role role,long[] permissionIds){
        rolePermissionService.setPermissions(role, permissionIds);
        roleService.update(role);
        return "redirect:role_list";
    }

    @RequestMapping("addRolePage")
    public String addRolePage(Model model){
        List<Permission> ps = permissionService.list();
        model.addAttribute("ps", ps);
        return "admin/addRole";
    }

    @RequestMapping("addRole")
    public String addRole(Role role,long[] permissionIds){
        roleService.add(role);
        rolePermissionService.setPermissions(role, permissionIds);
        return "redirect:role_list";
    }
    @RequestMapping("deleteRole")
    public String delete(Model model,long id){
        roleService.delete(id);
        return "redirect:role_list";
    }   
 
}