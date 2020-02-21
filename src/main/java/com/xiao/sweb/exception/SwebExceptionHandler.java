package com.xiao.sweb.exception;

import com.xiao.sweb.utills.SwebJSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class SwebExceptionHandler {

    public static final String ERROR_VIEW = "error";


//    @ExceptionHandler(value = Exception.class)
//    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
//        e.printStackTrace();
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception", e);
//        modelAndView.addObject("url",request.getRequestURL());
//        modelAndView.setViewName(ERROR_VIEW);
//        return modelAndView;
//    }

    @ExceptionHandler(value = Exception.class)
    public Object ajaxErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
        e.printStackTrace();

        if(isAjax(request)){
            return SwebJSONResult.errorException(e.getMessage());
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("exception", e);
            modelAndView.addObject("url",request.getRequestURL());
            modelAndView.setViewName(ERROR_VIEW);
            return modelAndView;
        }
    }

    public static boolean isAjax(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
