package com.milchstrabe.uranus.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.milchstrabe.uranus.common.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
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
        if(!StringUtils.hasLength(authorization)){
            authorization = request.getParameter("Authorization");
            if(!StringUtils.hasLength(authorization)){
                //TODO  miss token
                throw new AuthException("!");
            }
        }

        DecodedJWT decode = null;
        try {
            decode = JWT.decode(authorization);
        }catch (JWTDecodeException exception){
            log.error(exception.getMessage());
            //TODO new instance about
            throw new AuthException("");
        }
        //TODO decode session info from jwt token
        request.setAttribute("","");
        return true;
    }
}
