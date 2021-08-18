package com.milchstrabe.uranus.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author ch3ng
 * @Date 2021/8/18 20:05
 * @Version 1.0
 * @Description get current user info from request
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Session {
}
