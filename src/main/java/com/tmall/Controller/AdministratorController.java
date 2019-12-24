package com.tmall.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.pojo.Administrator;
import com.tmall.pojo.Role;
import com.tmall.service.AdministratorService;
import com.tmall.service.RoleService;
import com.tmall.service.UserRoleService;
import com.tmall.util.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;


    //管理员列表
    @RequiresPermissions("administrator_list")
    @RequestMapping("administrator_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Administrator> as= administratorService.list();
        int total = (int) new PageInfo<>(as).getTotal();
        page.setTotal(total);
        Map<Administrator,List<Role>> user_roles = new HashMap<>();
        for (Administrator administrator : as) {
            List<Role> roles= roleService.listRoles(administrator);
            user_roles.put(administrator, roles);
        }
        model.addAttribute("user_roles", user_roles);
        model.addAttribute("as", as);
        model.addAttribute("page", page);
        return "admin/listAdministrator";
    }
    //登录功能
    @RequestMapping("admin_login")
    public String login(Model model,String name, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
            Session session=subject.getSession();
            session.setAttribute("subject", subject);
            System.out.println("验证成功");
            return "redirect:main";
        } catch (AuthenticationException e) {
            System.out.println("验证失败");
            model.addAttribute("msg", "用户名或密码错误");
            return "admin/login";

        }
    }

    //注册功能
    @RequestMapping("admin_register")
    public String register(Model model, Administrator administrator) {
        String name = administrator.getName();
        //字符串转义
        name = HtmlUtils.htmlEscape(name);
        administrator.setName(name);
        boolean exist = administratorService.isExist(name);
        if (exist) {
            String m = "用户名已经被使用，不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("administrator", null);
            return "admin/register";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName,administrator.getPassword(),salt,times).toString();
        administrator.setPassword(encodedPassword);
        administrator.setSalt(salt);
        administratorService.add(administrator);
        return "redirect:login";
    }

    //添加管理员
    @RequiresPermissions("addAdministrator")
    @RequestMapping("addAdministrator")
    public String add(Model model, Administrator administrator,long[] roleIds){
        String name = administrator.getName();
        //字符串转义
        name = HtmlUtils.htmlEscape(name);
        administrator.setName(name);
        boolean exist = administratorService.isExist(name);
        if (exist) {
            String m = "用户名已经被使用，不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("administrator", null);
            return "redirect:addAdministratorPage";
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName,administrator.getPassword(),salt,times).toString();
        administrator.setPassword(encodedPassword);
        administrator.setSalt(salt);
        administratorService.add(administrator);
        userRoleService.setRoles(administrator,roleIds);
        return "redirect:administrator_list";
    }

    //添加管理员页面
    @RequiresPermissions("addAdministratorPage")
    @RequestMapping("addAdministratorPage")
    public String addAdministratorPage(Model model){
        List<Role> rs = roleService.list();
        model.addAttribute("rs", rs);
        return "admin/addAdministrator";
    }

    //删除管理员
    @RequiresPermissions("deleteAdministrator")
    @RequestMapping("deleteAdministrator")
    public String delete(Model model,long id){
        administratorService.delete(id);
        return "redirect:administrator_list";
    }

    //分配角色页面（编辑页面）
    @RequiresPermissions("editAdministratorPage")
    @RequestMapping("editAdministratorPage")
    public String editAdministratorPage(Model model,Long id){
        List<Role> rs = roleService.list();
        model.addAttribute("rs", rs);
       Administrator administrator =administratorService.get(id);
        model.addAttribute("administrator", administrator);
        List<Role> roles =roleService.listRoles(administrator);
        model.addAttribute("currentRoles", roles);
        return "admin/editAdministrator";
    }
    //修改管理员
    @RequiresPermissions("updateAdminitrator")
    @RequestMapping("updateAdminitrator")
    public String update(Administrator administrator,long[] roleIds){
        userRoleService.setRoles(administrator,roleIds);

        String password=administrator.getPassword();
        //如果在修改的时候没有设置密码，就表示不改动密码
        if(administrator.getPassword() != "******") {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
            administrator.setSalt(salt);
            administrator.setPassword(encodedPassword);
        }
        else
            administrator.setPassword(null);

        administratorService.update(administrator);

        return "redirect:administrator_list";

    }
}
