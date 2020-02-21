package com.xiao.sweb.service;

import com.xiao.sweb.entity.Books;

import java.util.List;

public interface BooksService {

    int deleteByPrimaryKey(Integer id);

    int insert(Books record);

    Books selectByPrimaryKey(Integer id);

    List<Books> selectAll();

    int updateByPrimaryKey(Books record);
}
