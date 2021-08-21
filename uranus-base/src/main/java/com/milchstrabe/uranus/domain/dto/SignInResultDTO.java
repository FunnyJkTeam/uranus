package com.milchstrabe.uranus.domain.dto;

import com.milchstrabe.uranus.domain.po.RefreshToken;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignInResultDTO {

    private String token;
    private RefreshToken refreshToken;
}
