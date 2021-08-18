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
        return Result.builder().build().fill(Status._200);
    }

    public static Result OK(Object data){
        return Result.builder().data(data).build().fill(Status._200);
    }

    public static Result customize(Status status,Object data){
        return Result.builder().data(data).build().fill(status);
    }

    public static Result customize(Status status){
        return Result.builder().build().fill(status);
    }
}
