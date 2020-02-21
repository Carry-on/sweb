package com.xiao.sweb.service;

import com.xiao.sweb.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {

    long countByExample(Users users);

    int deleteByExample(Users example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(Users example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") Users example);

    int updateByExample(@Param("record") Users record, @Param("example") Users example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}
