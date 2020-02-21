package com.xiao.sweb.dao;

import com.xiao.sweb.entity.Books;

import java.util.List;

public interface BooksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

    List<Books> selectAll();
}