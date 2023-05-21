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

}
