package com.tmall.mapper;

import com.tmall.pojo.Administrator;
import com.tmall.pojo.AdministratorExample;

import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    List<Administrator> selectByExample(AdministratorExample example);

    Administrator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    Administrator getByName(String name);
}
