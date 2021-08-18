package com.milchstrabe.uranus.common.result;

public enum Status {
    _200(200,"success"),

    _301(301,"token err"),
    _302(302,"token time out"),
    _303(303,"params err"),
    _500(500,"internal err");

    Status(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    protected Integer code;
    protected String msg;
}
