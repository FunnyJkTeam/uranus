package com.milchstrabe.uranus.common;

import com.milchstrabe.uranus.common.exception.auth.AuthException;
import com.milchstrabe.uranus.common.exception.auth.LostTokenException;
import com.milchstrabe.uranus.common.exception.auth.TokenTimeOutException;
import com.milchstrabe.uranus.common.result.Result;
import com.milchstrabe.uranus.common.result.ResultBuilder;
import com.milchstrabe.uranus.common.result.ResultEnum;
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
    public Result AuthExceptionHandler(AuthException exception){
        if(exception instanceof LostTokenException){
            return ResultBuilder.build(ResultEnum._300);
        }else if(exception instanceof TokenTimeOutException){
            return ResultBuilder.build(ResultEnum._302);
        }else {
            return ResultBuilder.build(ResultEnum._301);
        }
    }

    @ExceptionHandler({Exception.class})
    public Result exception(Exception ex) {
        log.error("eror:",ex);
        return null;
    }
}
