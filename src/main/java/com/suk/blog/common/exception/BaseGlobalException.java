package com.suk.blog.common.exception;


import com.suk.blog.common.response.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseGlobalException extends Exception {

    private Logger log = LoggerFactory.getLogger(getClass());


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxResult invalidParamException(Exception e) {
        log.error("exception {}", e);
        return AjaxResult.error();
    }


}
