package com.xiao.sweb.common;

import com.xiao.sweb.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestfulResponse<T> {

  @ApiModelProperty(value = "状态码", required = true)
  private int code = 0;

  @ApiModelProperty(value = "消息", required = true)
  private String message = "sucess";

  @ApiModelProperty(value = "数据集合", required = true)
  private T data;

  public static RestfulResponse systemError(){
    RestfulResponse<String> restfulResponse = new RestfulResponse<>();
    restfulResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
    restfulResponse.setMessage(ResultEnum.SERVER_ERROR.getMessage());
    return restfulResponse;
  }

  public static RestfulResponse error(Integer code, String message){
    RestfulResponse response = new RestfulResponse();
    response.setCode(code);
    response.setMessage(message);
    return response;
  }

  public static RestfulResponse error(ResultEnum resultEnum){
    return error(resultEnum.getCode(), resultEnum.getMessage());
  }

  public static RestfulResponse success(Integer code, String message, Object data){
    RestfulResponse<Object> response = new RestfulResponse();
    response.setCode(code);
    response.setMessage(message);
    response.setData(data);
    return response;
  }

  public static RestfulResponse success(ResultEnum resultEnum, Object data){
    return success(resultEnum.getCode(), resultEnum.getMessage(), data);
  }

  public static RestfulResponse success(Object data){
    return success(ResultEnum.SUCCESS, data);
  }



}
