package com.milchstrabe.uranus.common.result;

public enum ResultEnum {
    _200(200,"success"),

    _300(300,"lost token"),
    _301(301,"token err"),
    _302(302,"token timeout"),
    _303(303,"params err"),
    _304(304,"operation fail"),
    _500(500,"internal err");

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    protected Integer code;
    protected String msg;
}
