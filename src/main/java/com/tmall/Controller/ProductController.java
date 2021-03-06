package com.tmall.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.pojo.Category;
import com.tmall.pojo.Product;
import com.tmall.service.CategoryService;
import com.tmall.service.ProductService;
import com.tmall.util.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequiresPermissions("product_add")
    @RequestMapping("product_add")
    public String product_add(Model model,int id,String name){
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "admin/addProduct";
    }

    @RequiresPermissions("admin_product_add")
    @RequestMapping("admin_product_add")
    public String add(Product product){
        product.setCreateDate(new Date());
        productService.add(product);
        return  "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequiresPermissions("admin_product_delete")
    @RequestMapping("admin_product_delete")
    public String delete(int id){
        Product product = productService.get(id);
        productService.delete(id);
        return  "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequiresPermissions("admin_product_edit")
    @RequestMapping("admin_product_edit")
    public String edit(int id, Model model){
        Product product = productService.get(id);
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        model.addAttribute("p",product);
        return "admin/editProduct";
    }

    @RequiresPermissions("admin_product_update")
    @RequestMapping("admin_product_update")
    public String update(Product product){
        productService.update(product);
        return  "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequiresPermissions("admin_product_list")
    @RequestMapping("admin_product_list")
    public String list(int cid, Model model , Page page){
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps =  productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());
        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }
}
