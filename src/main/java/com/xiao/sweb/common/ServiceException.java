package com.xiao.sweb.common;

import com.example.recruit.enums.ResultEnum;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

  private int code;
  private String msg;

  public ServiceException(ResultEnum resCodeEnum) {
    this.code = resCodeEnum.getCode();
    this.msg = resCodeEnum.getMsg();
  }

  public ServiceException(String msg) {
    this.code = 500;
    this.msg = msg;
  }


  public ServiceException(Integer code,String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ServiceException() {
  }
}
