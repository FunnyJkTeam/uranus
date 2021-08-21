package com.milchstrabe.uranus.domain.req;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VCodeSignInReq {

    private AccountInfoReq accountInfo;
    private DeviceInfoReq deviceInfo;

}
