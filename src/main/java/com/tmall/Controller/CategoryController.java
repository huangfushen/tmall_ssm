package com.tmall.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tmall.pojo.Category;
import com.tmall.service.CategoryService;
import com.tmall.util.ImageUtil;
import com.tmall.util.Page;
import com.tmall.util.UploadedImageFile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categroyService;

    @RequiresPermissions("category_add")
    @RequestMapping("category_add")
    public String category_add(){
        return "admin/addCategory";
    }

    @RequiresPermissions("admin_category_list")
    @RequestMapping("admin_category_list")
    public String list(Model model,Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs= categroyService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequiresPermissions("admin_category_add")
    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile)throws IOException {
        categroyService.add(c);
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        //判断路径是否存在
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequiresPermissions("admin_category_delete")
    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) throws IOException {
        categroyService.delete(id);
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/admin_category_list";
    }

    @RequiresPermissions("admin_category_edit")
    @RequestMapping("admin_category_edit")
    public String edit(int id,Model model){
        Category c = categroyService.get(id);
        model.addAttribute("c",c);
        return "admin/editCategory";
    }

    @RequiresPermissions("admin_category_update")
    @RequestMapping("admin_category_update")
    public String update(Category category,HttpSession session, UploadedImageFile uploadedImageFile) throws IOException{
        categroyService.update(category);
        MultipartFile image = uploadedImageFile.getImage();
        if(null!=image &&!image.isEmpty()){
            File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,category.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
