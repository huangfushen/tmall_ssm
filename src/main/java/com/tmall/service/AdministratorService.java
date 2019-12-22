package com.tmall.service;

import com.tmall.pojo.Administrator;

import java.util.List;

public interface AdministratorService {
    public String getPassword(String name);
    public Administrator getByName(String name);
    void add(Administrator a);
    void delete(Long id);
    void update(Administrator a);
    Administrator get(Long id);
    List<Administrator> list();
    boolean isExist(String name);
    Administrator get(String name, String password);
    Administrator selectByPrimaryKey(Long id);
}
