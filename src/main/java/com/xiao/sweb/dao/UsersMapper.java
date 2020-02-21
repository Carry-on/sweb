package com.xiao.sweb.dao;

import com.xiao.sweb.entity.Users;
import com.xiao.sweb.entity.Users;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UsersMapper {
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