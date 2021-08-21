package com.milchstrabe.uranus.domain.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class RefreshToken {

    /**
     * account id
     */
    private Long id;
    private String refreshToken;
    private Date expiresAt;
}
