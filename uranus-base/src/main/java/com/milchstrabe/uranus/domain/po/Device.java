package com.milchstrabe.uranus.domain.po;

import com.milchstrabe.uranus.domain.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Device {
    private String id;
    private String deviceId;
    private DeviceType deviceType;
    private String os;
    private Long accountId;
}
