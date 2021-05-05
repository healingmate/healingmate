package com.mallang.healingmate.account.dto.request;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.ProfileImage;
import com.mallang.healingmate.keyword.domain.Keyword;
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

@Schema(description = "회원 정보 수정 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    @Schema(description = "회원 닉네임", example = "김말랑")
    @Length(min = 2, max = 10, message = "닉네임은 2자이상 10자 이하로 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message = "닉네임은 공백을 제외한 한글만 입력이 가능합니다.")
    @NotBlank
    String nickname;

    @Schema(description = "회원 프로필 이미지", example = "RABIT")
    @NotBlank
    String profileImage;

    @Schema(description = "회원 키워드", example = "['취업','학업/진로','가족']")
    List<String> keywords = new LinkedList<>();

    public Account toAccount() {
        return Account.builder()
                .nickname(nickname)
                .profileImage(ProfileImage.valueOf(profileImage))
                .build();
    }
}
