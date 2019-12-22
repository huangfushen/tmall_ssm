package com.tmall.service.impl;

import com.tmall.mapper.AdministratorMapper;
import com.tmall.pojo.Administrator;
import com.tmall.pojo.AdministratorExample;
import com.tmall.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    AdministratorMapper administratorMapper;

    @Override
    public String getPassword(String name) {
        Administrator administrator = getByName(name);
        if(null==administrator)
            return null;
        return administrator.getPassword();
    }

/*    @Override
    public Administrator getByName(String name) {
        Administrator administrator = administratorMapper.getByName(name);
        if(administrator ==null)
            return null;
        return administrator;
    }*/
    @Override
    public Administrator getByName(String name) {
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andNameEqualTo(name);
        List<Administrator> users = administratorMapper.selectByExample(example);
        if (users.isEmpty())
            return null;
        return users.get(0);
    }
    @Override
    public void add(Administrator a){
        administratorMapper.insert(a);
    }
    @Override
    public void delete(Long id){
        administratorMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void update(Administrator a){
        administratorMapper.updateByPrimaryKeySelective(a);
    }
    @Override
    public Administrator get(Long id){
        return administratorMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Administrator> list(){
        AdministratorExample example =new AdministratorExample();
        example.setOrderByClause("id desc");
        return administratorMapper.selectByExample(example);

    }
@Override
        public boolean isExist(String name){

            Administrator result = administratorMapper.getByName(name);
            if (result != null)
                return true;
            return false;
        }
    @Override
    public Administrator get(String name ,String password){
        AdministratorExample example = new AdministratorExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Administrator> result= administratorMapper.selectByExample(example);
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
    @Override
    public Administrator selectByPrimaryKey(Long id){
        Administrator administrator = administratorMapper.selectByPrimaryKey(id);
        return administrator;
    }
}
