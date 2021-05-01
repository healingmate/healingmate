package com.mallang.healingmate.account.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * com.mallang.healingmate.account.dto.response
 * AuthResponse.java
 * @date    2021-05-01 오후 8:33
 * @author  이아영
 *
 * @변경이력
 **/

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
