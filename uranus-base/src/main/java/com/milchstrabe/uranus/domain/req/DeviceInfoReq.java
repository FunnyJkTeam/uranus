package com.milchstrabe.uranus.domain.req;

import com.milchstrabe.uranus.domain.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceInfoReq {
    private DeviceType deviceType;
    private String deviceId;
    private String os;

}
