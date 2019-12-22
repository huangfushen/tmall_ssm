package com.tmall.service;
import com.tmall.pojo.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> list();

    public void add(Category category);

    public void delete(int id);

    public Category get(int id);

    public void update(Category category);

}
