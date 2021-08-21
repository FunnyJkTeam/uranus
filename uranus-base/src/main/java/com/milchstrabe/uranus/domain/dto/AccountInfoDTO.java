package com.milchstrabe.uranus.domain.dto;

import com.milchstrabe.uranus.domain.AccountType;
import com.milchstrabe.uranus.domain.po.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountInfoDTO {
    private AccountType accountType;
    private String account;
    private String vCode;
}
