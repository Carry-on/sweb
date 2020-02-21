package com.xiao.sweb.service.impl;

import com.xiao.sweb.dao.BooksMapper;
import com.xiao.sweb.entity.Books;
import com.xiao.sweb.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksMapper booksMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Books record) {
        return 0;
    }

    @Override
    public Books selectByPrimaryKey(Integer id) {
        return booksMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Books> selectAll() {
        return booksMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Books record) {
        return 0;
    }
}
