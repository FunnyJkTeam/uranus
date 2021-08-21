package com.milchstrabe.uranus.domain;

public enum DeviceType {

    PC("PC"),
    MOBILE("MOBILE"),
    WEB("WEB");


    private String type;

    DeviceType(String type) {
        this.type = type;
    }
}
