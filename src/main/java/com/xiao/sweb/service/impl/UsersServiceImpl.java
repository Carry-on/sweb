package com.xiao.sweb.service.impl;

import com.xiao.sweb.dao.UsersMapper;
import com.xiao.sweb.entity.Users;
import com.xiao.sweb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public long countByExample(Users users) {
        return 0;
    }

    @Override
    public int deleteByExample(Users example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Users record) {
        return usersMapper.insert(record);
    }

    @Override
    public int insertSelective(Users record) {
        return 0;
    }

    @Override
    public List<Users> selectByExample(Users example) {
        return null;
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Users record, Users example) {
        return 0;
    }

    @Override
    public int updateByExample(Users record, Users example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return 0;
    }
}
