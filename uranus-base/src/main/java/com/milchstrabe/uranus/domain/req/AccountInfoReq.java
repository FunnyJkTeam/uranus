package com.milchstrabe.uranus.domain.req;

import com.milchstrabe.uranus.domain.AccountType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountInfoReq {
    private AccountType accountType;
    private String account;
    private String vCode;

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
