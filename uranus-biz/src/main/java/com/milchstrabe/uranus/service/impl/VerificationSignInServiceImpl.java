package com.milchstrabe.uranus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.milchstrabe.uranus.common.exception.logic.LogicException;
import com.milchstrabe.uranus.domain.dto.AccountInfoDTO;
import com.milchstrabe.uranus.domain.dto.DeviceDTO;
import com.milchstrabe.uranus.domain.dto.SignInResultDTO;
import com.milchstrabe.uranus.domain.po.Account;
import com.milchstrabe.uranus.domain.po.Device;
import com.milchstrabe.uranus.domain.po.RefreshToken;
import com.milchstrabe.uranus.service.AbstractSignInService;
import org.springframework.stereotype.Service;

@Service
public class VerificationSignInServiceImpl extends AbstractSignInService {

    @Override
    public SignInResultDTO signIn(AccountInfoDTO accountDto, DeviceDTO deviceDto) throws LogicException {
        //校验验证码
        boolean isSuccess = verifyCode(accountDto);
        if(!isSuccess){
            throw new LogicException("验证码错误");
        }
        //检查账户
        Account account = checkAccount(accountDto);
        //生成jwt
        Device device = BeanUtil.copyProperties(deviceDto, Device.class);
        device.setAccountId(account.getId());
        String jwt = createJWT(account, device);
        //保存设备信息
        saveDeviceInfo(device);
        //保存refreshToken
        RefreshToken refreshToken = createRefreshToken(account);

        return SignInResultDTO.builder().refreshToken(refreshToken).token(jwt).build();
    }
}
