package com.xiao.sweb.filter;

import com.xiao.sweb.common.RestfulResponse;
import com.xiao.sweb.common.ServiceException;
import com.xiao.sweb.enums.ResultEnum;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Api(value = "全局异常处理")
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    RestfulResponse exceptionHandler(Exception e) {
        log.error("发生异常 e={}", e);
        return RestfulResponse.systemError();
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    RestfulResponse ServiceExceptionHandler(ServiceException e) {
        return RestfulResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    RestfulResponse exceptionHandler(MethodArgumentNotValidException e) {
        return RestfulResponse.error(ResultEnum.PARAM_VERIFY_FALL.getCode(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
