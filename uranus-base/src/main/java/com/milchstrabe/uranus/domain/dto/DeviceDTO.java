package com.milchstrabe.uranus.domain.dto;

import com.milchstrabe.uranus.domain.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeviceDTO {
    private String deviceId;
    private DeviceType deviceType;
    private String os;
    private Long accountId;
}
