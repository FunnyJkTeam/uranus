package com.milchstrabe.uranus.common.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    protected Result fill(ResultEnum resultEnum){
        this.code = resultEnum.code;
        this.msg = resultEnum.msg;
        return this;
    }

}
