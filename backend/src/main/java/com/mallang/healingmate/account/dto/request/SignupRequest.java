package com.mallang.healingmate.account.dto.request;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * com.mallang.healingmate.account.dto.request
 * SignupRequest.java
 * @date    2021-05-01 오후 8:33
 * @author  이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 가입 요청")
public class SignupRequest {

    @Schema(description = "회원 아이디")
    @Length(min = 5, max = 16, message = "아이디는 5자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9+]*$",message = "아이디는 공백을 제외한 영문과 숫자만 입력이 가능합니다.")
    @NotBlank
    String userId;

    @Schema(description = "회원 닉네임")
    @Length(min = 2,max=10,message = "닉네임은 2자이상 10자 이하로 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$",message = "닉네임은 공백을 제외한 한글만 입력이 가능합니다.")
    @NotBlank
    String nickname;

    @Schema(description = "회원 비밀번호")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    String password;

    @Schema(description = "회원 출생년도")
    //@Pattern(regexp = "^(19|20)\\d{2}$",message = "출생년도는 숫자4자리만 입력이 가능합니다.")
    @NotNull
    Integer birthYear;

    public Account toAccount(){
        return Account.builder()
                .userId(userId)
                .nickname(nickname)
                .password(password)
                .birthYear(birthYear)
                .role(UserRole.ROLE_USER)
                //TODO: 처음 회원가입 시 디폴트 이미지를 넣을 것인지?
                .score(0)
                .build();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
