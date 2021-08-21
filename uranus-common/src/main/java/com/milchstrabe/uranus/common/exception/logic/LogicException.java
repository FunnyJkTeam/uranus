package com.milchstrabe.uranus.common.exception.logic;

public class LogicException extends Exception{

    public final int CODE = 304;
    public LogicException(String msg){
        super(msg);
    }
}
