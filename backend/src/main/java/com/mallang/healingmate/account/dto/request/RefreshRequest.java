package com.mallang.healingmate.account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * com.mallang.healingmate.account.dto.request
 * RefreshRequest.java
 * @date    2021-05-02 오후 10:02
 * @author  이아영
 *
 * @변경이력
 **/

@Schema(description = "Access Token 재발급 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshRequest {
    @Schema(description = "Refresh Token")
    @NotBlank
    private String refreshToken;
}
