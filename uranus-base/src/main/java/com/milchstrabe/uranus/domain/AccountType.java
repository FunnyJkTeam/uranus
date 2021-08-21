package com.milchstrabe.uranus.domain;

public enum AccountType {


    PHONE("PHONE"),
    EMAIL("EMAIL"),
    WECHAT("WECHAT"),
    QQ("QQ"),
    GOOGLE("GOOGLE"),
    GITHUB("GITHUB");

    private String type;

    AccountType(String type) {
        this.type = type;
    }
}
