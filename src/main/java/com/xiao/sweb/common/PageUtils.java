package com.xiao.sweb.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;

/**
 * 分页工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  //总记录数
  private int totalCount;
  //每页记录数
  private int pageSize;
  //总页数
  private int totalPage;
  //当前页数
  private int currPage;
  //列表数据
  private T list;

  public PageUtils() {

  }

  /**
   * 分页
   *
   * @param list       列表数据
   * @param totalCount 总记录数
   * @param pageSize   每页记录数
   * @param currPage   当前页数
   */
  public PageUtils(T list, int totalCount, int pageSize, int currPage) {
    this.list = list;
    this.totalCount = totalCount;
    this.pageSize = pageSize;
    this.currPage = currPage;
    this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
  }

  /**
   * 分页
   */
  public PageUtils(IPage<T> page) {
    this.list = (T) page.getRecords();
    this.totalCount = (int) page.getTotal();
    this.pageSize = (int) page.getSize();
    this.currPage = (int) page.getCurrent();
    this.totalPage = (int) page.getPages();
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getCurrPage() {
    return currPage;
  }

  public void setCurrPage(int currPage) {
    this.currPage = currPage;
  }

  public T getList() {
    return list;
  }

  public void setList(T list) {
    this.list = list;
  }

}
