package com.milchstrabe.uranus.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignInVO {
    private String token;
    private RefreshTokenVO refreshToken;
}
