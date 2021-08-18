package com.milchstrabe.uranus.common;

import com.milchstrabe.uranus.common.exception.AuthException;
import com.milchstrabe.uranus.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Author ch3ng
 * @Date 2021/8/18 20:04
 * @Version 1.0
 * @Description global exception handler
 **/
@Slf4j
@RestControllerAdvice
public class LogicExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    public Result internalException(AuthException exception){
        //TODO
        return null;
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception ex) {
        log.error("eror:",ex);
        return null;
    }
}
