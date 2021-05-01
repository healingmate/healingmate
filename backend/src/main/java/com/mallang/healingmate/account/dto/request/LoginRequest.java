package com.mallang.healingmate.account.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * com.mallang.healingmate.account.dto.request
 * LoginRequest.java
 * @date    2021-05-01 오후 8:33
 * @author  이아영
 *
 * @변경이력
 **/

@Schema(description = "로그인 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {
    @Schema(description = "회원 아이디", example = "abcde1234")
    @Length(min = 5, max = 16, message = "아이디는 5자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    private String userId;

    @Schema(description = "회원 비밀번호", example = "abcde1234")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    private String password;
}
