package com.mallang.healingmate.account.dto.request;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.ProfileImage;
import com.mallang.healingmate.account.domain.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Random;

/**
 * com.mallang.healingmate.account.dto.request
 * SignupRequest.java
 *
 * @author 이아영
 * @date 2021-05-01 오후 8:33
 * @변경이력
 **/

@Schema(description = "회원 가입 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupRequest {

    @Schema(description = "회원 아이디", example = "abcde1234")
    @Length(min = 5, max = 16, message = "아이디는 5자 이상 16자 이하로 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9+]*$", message = "아이디는 공백을 제외한 영문과 숫자만 입력이 가능합니다.")
    @NotBlank
    String userId;

    @Schema(description = "회원 닉네임", example = "김말랑")
    @Length(min = 2, max = 10, message = "닉네임은 2자이상 10자 이하로 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "닉네임은 공백을 제외한 한글만 입력이 가능합니다.")
    @NotBlank
    String nickname;

    @Schema(description = "회원 비밀번호", example = "abcde1234")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    String password;

    @Schema(description = "회원 출생년도", example = "1997")
    @Max(value = 2099)
    @Min(value = 1900)
    @NotNull
    Integer birthYear;

    public Account toAccount() {
        return Account.builder()
                .userId(userId)
                .nickname(nickname)
                .password(password)
                .birthYear(birthYear)
                .role(UserRole.ROLE_USER)
                .profileImage(ProfileImage.values()[new Random().nextInt(ProfileImage.values().length)])
                .score(0)
                .build();
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
