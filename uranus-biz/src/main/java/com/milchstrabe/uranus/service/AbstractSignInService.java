package com.milchstrabe.uranus.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import com.milchstrabe.uranus.config.JWTConfig;
import com.milchstrabe.uranus.domain.dto.AccountInfoDTO;
import com.milchstrabe.uranus.domain.po.Account;
import com.milchstrabe.uranus.domain.po.Device;
import com.milchstrabe.uranus.domain.po.RefreshToken;
import com.milchstrabe.uranus.repository.AccountRepository;
import com.milchstrabe.uranus.repository.DeviceRepository;
import com.milchstrabe.uranus.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public abstract class AbstractSignInService implements ISiginService{

    private static final String KEY = "SIGN_IN";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    protected boolean verifyCode(AccountInfoDTO accountInfoDto){
        StringBuilder redisKey = new StringBuilder(KEY)
                .append(":")
                .append(accountInfoDto.getAccount());
        String code = redisTemplate.opsForValue().get(redisKey.toString());
        if(accountInfoDto.getVCode().equals(code)){
            redisTemplate.delete(redisKey.toString());
            return true;
        }
        return false;
    }

    protected Account checkAccount(AccountInfoDTO accountDto){
        //用户是否存在
        Account account = accountRepository.findAccountBy(accountDto.getAccountType(), accountDto.getAccount());
        if (account == null){
            account = new Account();
            switch (accountDto.getAccountType()){
                case PHONE:
                    account.setPhone(accountDto.getAccount());
                    break;
                case EMAIL:
                    account.setEmail(accountDto.getAccount());
                    break;
            }
            Snowflake snowflake = IdUtil.getSnowflake(1, 1);
            account.setId(snowflake.nextId());
            accountRepository.addAccount(account);
        }
        return account;
    }

    protected String createJWT(Account account, Device device){
        Date date = null;
        switch (device.getDeviceType()){
            case PC:
                date = DateUtil.offsetDay(new Date(), 7);
                break;
            case WEB:
                date = DateUtil.offsetMinute(new Date(), 18);
                break;
            case MOBILE:
                date = DateUtil.offsetMonth(new Date(), 3);
                break;
            default:
                date = DateUtil.offsetMinute(new Date(), 18);
        }
        String jwt = JWT.create()
                .setPayload("accountId", account.getId())
                .setKey(JWTConfig.secret.getBytes(StandardCharsets.UTF_8))
                .setExpiresAt(date)
                .sign();
        return jwt;
    }

    protected RefreshToken createRefreshToken(Account account){
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByAccountId(account.getId());
        if(refreshToken == null || DateUtil.compare(new Date(),refreshToken.getExpiresAt()) > 0){
            refreshToken = RefreshToken.builder()
                    .refreshToken(IdUtil.simpleUUID())
                    .id(account.getId())
                    .expiresAt(DateUtil.offsetMonth(new Date(), 6))
                    .build();
            refreshTokenRepository.addRefreshToken(refreshToken);
        }
        return refreshToken;
    }

    protected void saveDeviceInfo(Device device){
        Device deviceInDatabase = deviceRepository.findDeviceByDeviceId(device.getDeviceId());
        if(deviceInDatabase != null){
            BeanUtil.copyProperties(device,deviceInDatabase,"id","deviceId");
            deviceRepository.addDevice(deviceInDatabase);
            return;
        }
        deviceRepository.addDevice(device);
    }
}
