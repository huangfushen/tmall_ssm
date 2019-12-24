package com.tmall.Controller;
 
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.util.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.tmall.pojo.Permission;
import com.tmall.service.PermissionService;
 
@Controller
@RequestMapping("")
public class PermissionController {
    @Autowired PermissionService permissionService;

    @RequiresPermissions("permission_list")
    @RequestMapping("permission_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Permission> ps= permissionService.list();
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        model.addAttribute("ps", ps);
        return "admin/listPermission";
    }

    @RequiresPermissions("editPermission")
    @RequestMapping("editPermission")
    public String list(Model model,long id){
        Permission permission =permissionService.get(id);
        model.addAttribute("permission", permission);
        return "admin/editPermission";
    }

    @RequiresPermissions("updatePermission")
    @RequestMapping("updatePermission")
    public String update(Permission permission){

        permissionService.update(permission);
        return "redirect:permission_list";
    }
    //添加权限页面
    @RequiresPermissions("addPermissionPage")
    @RequestMapping("addPermissionPage")
    public String addPermissionPage(){
        return "admin/addPermission";
    }

    @RequiresPermissions("addPermission")
    @RequestMapping("addPermission")
    public String list(Model model,Permission permission){
        System.out.println(permission.getName());
        System.out.println(permission.getDesc_());
        permissionService.add(permission);
        return "redirect:permission_list";
    }

    @RequiresPermissions("deletePermission")
    @RequestMapping("deletePermission")
    public String delete(Model model,long id){
        permissionService.delete(id);
        return "redirect:permission_list";
    }   
 
}