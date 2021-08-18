package com.milchstrabe.uranus.common.result;

import lombok.Builder;

@Builder
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    protected Result fill(Status status){
        this.code = status.code;
        this.msg = status.msg;
        return this;
    }

}
