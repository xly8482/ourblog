package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截捕捉自定义异常 CustomException.class
     */
    @ExceptionHandler(value = CustomException.class)
    public Map<String, Object> customExceptionHandler(CustomException ex)
    {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", "500");
        ret.put("desc", ex.getMsg());
        ret.put("exception", ex.toString());
        ex.printStackTrace();
        logger.info("====================" + ex.toString() + ":" + ex.getMessage());
        logger.debug("com.example.demo.exception.CustomException", ex);
        return ret;
    }

    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> globalExceptionHandler(Exception ex)
    {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", "500");
        ret.put("desc", "internal server error");
        ret.put("exception", ex.toString());
        ex.printStackTrace();
        logger.info("====================" + ex.toString() + ":" + ex.getMessage());
        logger.debug("java.lang.Exception", ex);
        return ret;
    }
}
