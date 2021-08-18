package com.milchstrabe.uranus.domain;

public enum DeviceType {

    PC("pc"),
    MOBILE("mobile"),
    WEB("web");


    private String type;

    DeviceType(String type) {
        this.type = type;
    }
}
