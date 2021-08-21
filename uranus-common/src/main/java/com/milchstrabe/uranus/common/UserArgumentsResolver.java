package com.milchstrabe.uranus.common;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import com.milchstrabe.uranus.common.exception.auth.LostTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ch3ng
 * @Date 2021/8/18 20:20
 * @Version 1.0
 * @Description 
 **/
@Component
@Slf4j
public class UserArgumentsResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Session.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest nativeRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = nativeRequest.getHeader("Authorization");
        if(StrUtil.isBlank(authorization)){
            authorization = nativeRequest.getParameter("Authorization");
            if(StrUtil.isBlank(authorization)){
                throw new LostTokenException();
            }
        }
        JWT jwt = JWT.of(authorization);
        Long accountId = NumberUtil.parseLong(jwt.getPayload("accountId").toString());
        Context context = Context.builder().accountId(accountId).build();
        return context;
    }
}
