package com.tmall.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.pojo.Administrator;
import com.tmall.service.AdministratorService;
import com.tmall.util.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;


    //管理员列表
    @RequestMapping("administrator_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<Administrator> as= administratorService.list();

        int total = (int) new PageInfo<>(as).getTotal();
        page.setTotal(total);

        model.addAttribute("as", as);
        System.out.println(as);
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
            model.addAttribute("msg", "验证失败");
            return "redirect:login";

        }
    }
/*    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        System.out.println(name);
        System.out.println(password);

        Administrator administrator = administratorService.get(name, password);

        if (null == administrator) {
            model.addAttribute("msg", "账号或密码错误");
            return "/login";
        }
        session.setAttribute("administrator", administrator);
        return "redirect:main";
    }*/
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
        administratorService.add(administrator);
        return "redirect:login";
    }

}
