package com.mallang.healingmate.account.dto.request;

import com.mallang.healingmate.account.domain.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.LinkedList;
import java.util.List;

/**
 * com.mallang.healingmate.account.dto.request
 * AccountUpdateRequest.java
 * @date    2021-05-04 오전 6:27
 * @author  이아영
 *
 * @변경이력
 **/

@Schema(description = "비밀번호 변경 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUpdateRequest {
    @Schema(description = "회원 현재 비밀번호", example = "abcde1234")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    String password;

    @Schema(description = "회원 새 비밀번호", example = "abcde1234567")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    @NotBlank
    String newPassword;
}
