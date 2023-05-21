package com.xiao.sweb.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("查询对象父类")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseQueryRequest {

  @ApiModelProperty(hidden = true, name = "userId", value = "当前登录用户Id（后台自动填充）")
  private Long userId;
  @ApiModelProperty(name = "page", value = "页码，从1开始", example = "1")
  private Integer page = 1;
  @ApiModelProperty(name = "rows", value = "每页条数，默认为10", example = "10")
  private Integer rows = 10;

  @ApiModelProperty("用户角色")
  private String role;
}
