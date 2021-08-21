package com.milchstrabe.uranus.api.sys;

import cn.hutool.core.bean.BeanUtil;
import com.milchstrabe.uranus.common.exception.logic.LogicException;
import com.milchstrabe.uranus.common.result.Result;
import com.milchstrabe.uranus.common.result.ResultBuilder;
import com.milchstrabe.uranus.common.result.ResultEnum;
import com.milchstrabe.uranus.domain.dto.AccountInfoDTO;
import com.milchstrabe.uranus.domain.dto.DeviceDTO;
import com.milchstrabe.uranus.domain.dto.SignInResultDTO;
import com.milchstrabe.uranus.domain.req.AccountInfoReq;
import com.milchstrabe.uranus.domain.req.DeviceInfoReq;
import com.milchstrabe.uranus.domain.req.VCodeSignInReq;
import com.milchstrabe.uranus.domain.vo.RefreshTokenVO;
import com.milchstrabe.uranus.domain.vo.SignInVO;
import com.milchstrabe.uranus.service.ISiginService;
import com.milchstrabe.uranus.service.impl.VerificationSignInServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/sys")
public class VerificationSignInRes {

    @Resource(type = VerificationSignInServiceImpl.class)
    ISiginService siginService;


    @PostMapping("/v1/auth/code")
    public Result signIn(@RequestBody @Validated VCodeSignInReq req){
        AccountInfoReq accountInfo = req.getAccountInfo();
        DeviceInfoReq deviceInfo = req.getDeviceInfo();
        try {
            AccountInfoDTO accountInfoDTO = BeanUtil.copyProperties(accountInfo, AccountInfoDTO.class);
            DeviceDTO deviceDTO = BeanUtil.copyProperties(deviceInfo, DeviceDTO.class);
            SignInResultDTO signInResultDto = siginService.signIn(accountInfoDTO,deviceDTO);

            RefreshTokenVO refreshTokenVO = RefreshTokenVO.builder().refreshToken(signInResultDto.getRefreshToken().getRefreshToken())
                    .expiresAt(signInResultDto.getRefreshToken().getExpiresAt().getTime())
                    .build();
            return ResultBuilder.OK(
                    SignInVO.builder()
                    .token(signInResultDto.getToken())
                    .refreshToken(refreshTokenVO)
                    .build()
            );
        } catch (LogicException e) {
            log.info("account:【{}】，sign fail;",accountInfo.getAccount(),e);
            return ResultBuilder.build(ResultEnum._304,e.getMessage());
        }
    }
}
