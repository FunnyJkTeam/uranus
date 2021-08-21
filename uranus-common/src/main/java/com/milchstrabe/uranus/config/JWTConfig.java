package com.milchstrabe.uranus.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig {

    public static String secret;


    @Value("${biz.jwt.secret}")
    public void setSecret(String secret){
        this.secret = secret;
    }
}
