package com.xiao.sweb.controller;

import com.github.pagehelper.PageHelper;
import com.xiao.sweb.entity.Books;
import com.xiao.sweb.service.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@MapperScan("com.xiao.sweb.dao.*.mapper")
//@Controller
//@RequestMapping("sweb")
@RestController
@Api(tags = "测试接口")
@ControllerAdvice
public class Hello {

    @Autowired
    private BooksService booksService;

    @RequestMapping("/hello")
    @ApiOperation(value = "Hello World！接口", notes = "hello", httpMethod = "GET")
    public String Hello(){
        return "Hello World!";
    }

    @RequestMapping("/getBooksName/{id}")
    @ApiOperation(value = "测试mybatis 链接接口", notes = "测试mybatis 链接接口", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "book id", defaultValue = "1", required = true)
    public String getBookName(@PathVariable(value = "id") int id){
        Books books = booksService.selectByPrimaryKey(id);
        return books.getTitle();
    }

    @RequestMapping("/error111")
    @ApiOperation(value = "异常捕获接口", notes = "异常捕获接口", httpMethod = "GET")
    public String error(){
        int[] a = {};
        int i = a[1];
        return "thymeleaf/error";
    }

    @RequestMapping("getBooksByPage/{pageNum}/{pageSize}")
    @ApiOperation(value = "pageHelper 分页接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page num", value = "pageNum", defaultValue = "1"),
            @ApiImplicitParam(name = "page size", value = "pageSize", defaultValue = "2", required = true)
    })
    public List<Books> getBooksByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Books> list =  booksService.selectAll();
        return list;
    }

}
