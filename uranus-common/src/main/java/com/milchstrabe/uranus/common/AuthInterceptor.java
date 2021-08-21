package com.milchstrabe.uranus.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.milchstrabe.uranus.common.exception.auth.AuthException;
import com.milchstrabe.uranus.common.exception.auth.LostTokenException;
import com.milchstrabe.uranus.common.exception.auth.TokenErrException;
import com.milchstrabe.uranus.common.exception.auth.TokenTimeOutException;
import com.milchstrabe.uranus.config.JWTConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Ø
 * @Author ch3ng
 * @Date 2021/8/18 20:14
 * @Version 1.0
 * @Description
 **/
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthException {
        String authorization = request.getHeader("Authorization");
        if(StrUtil.isBlank(authorization)){
            authorization = request.getParameter("Authorization");
            if(StrUtil.isBlank(authorization)){
                throw new LostTokenException();
            }
        }
        byte[] key = JWTConfig.secret.getBytes();

        boolean validate = JWT.of(authorization).setKey(key).validate(0);
        if(!validate){
            throw new TokenErrException();
        }

        return true;
    }
}
