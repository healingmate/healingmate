package com.mallang.healingmate.account.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthResponse {
    private String accessToken;
    private Long expireTime;
    private String refreshToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
