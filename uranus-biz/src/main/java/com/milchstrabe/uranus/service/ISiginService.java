package com.milchstrabe.uranus.service;

import com.milchstrabe.uranus.common.exception.logic.LogicException;
import com.milchstrabe.uranus.domain.dto.AccountInfoDTO;
import com.milchstrabe.uranus.domain.dto.DeviceDTO;
import com.milchstrabe.uranus.domain.dto.SignInResultDTO;

public interface ISiginService {


    SignInResultDTO signIn(AccountInfoDTO accountDto, DeviceDTO deviceDto) throws LogicException;
}
