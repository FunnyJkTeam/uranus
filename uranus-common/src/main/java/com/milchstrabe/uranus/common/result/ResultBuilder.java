package com.milchstrabe.uranus.common.result;

/**
 * @Author ch3ng
 * @Date 2021/8/18 10:13
 * @Version 1.0
 * @Description
 **/
public class ResultBuilder {


    private ResultBuilder(){}

    public static Result OK(){
        return Result.builder().build().fill(ResultEnum._200);
    }

    public static Result OK(Object data){
        return Result.builder().data(data).build().fill(ResultEnum._200);
    }

    public static Result build(ResultEnum resultEnum,Object data){
        return Result.builder().data(data).build().fill(resultEnum);
    }

    public static Result build(ResultEnum resultEnum){
        return Result.builder().build().fill(resultEnum);
    }
}
