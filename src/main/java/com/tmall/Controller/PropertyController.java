package com.tmall.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.pojo.Category;
import com.tmall.pojo.Property;
import com.tmall.service.CategoryService;
import com.tmall.service.PropertyService;
import com.tmall.util.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    PropertyService propertyService;

    @RequiresPermissions("property_add")
    @RequestMapping("property_add")
    public String property_add(Model model,int cid,String name){
        model.addAttribute("cid", cid);
        model.addAttribute("name", name);
        return "admin/addProperty";
    }

    @RequiresPermissions("admin_property_add")
    @RequestMapping("admin_property_add")
    public String add(Property property){
        propertyService.add(property);
        return  "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequiresPermissions("admin_property_delete")
    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return  "redirect:admin_property_list?cid="+property.getCid();

    }

    @RequiresPermissions("admin_property_edit")
    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("p",property);
        return "admin/editProperty";
    }

    @RequiresPermissions("admin_property_update")
    @RequestMapping("admin_property_update")
    public String update(Property property){
        propertyService.update(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequiresPermissions("admin_property_list")
    @RequestMapping("admin_property_list")
    public String list(int cid, Model model , Page page){
        Category c = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }
}
