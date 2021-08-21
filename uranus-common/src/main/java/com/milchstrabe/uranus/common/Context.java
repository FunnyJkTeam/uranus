package com.milchstrabe.uranus.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ch3ng
 * @Date 2021/8/18 20:20
 * @Version 1.0
 * @Description des user info in request from jwt
 **/
@Setter
@Getter
@Builder
public class Context {
    private Long accountId;
}
