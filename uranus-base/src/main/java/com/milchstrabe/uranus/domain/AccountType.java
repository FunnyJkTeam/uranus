package com.milchstrabe.uranus.domain;

public enum AccountType {


    PHONE("phone"),
    EMAIL("email"),
    WECHAT("wechat"),
    QQ("qq"),
    GOOGLE("google"),
    GITHUB("github");

    private String type;

    AccountType(String type) {
        this.type = type;
    }
}
