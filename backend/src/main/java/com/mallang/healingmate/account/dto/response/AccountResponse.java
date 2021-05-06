package com.mallang.healingmate.account.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.keyword.domain.AccountKeywords;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * com.mallang.healingmate.article.dto.response
 * AccountResponse.java
 *
 * @author 서범석
 * @date 2021-05-06 오후 4:38
 * @변경이력
 * 21-05-07 유저의 프로필 이미지를 고정된 이미지로 전환함으로서 enum타입으로 변경
 * 21-05-07 String[]을 편리성을 위해 List<String>으로 변경, profileImage 및 keyword 변환 추가
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResponse {
    private String userId;
    private String profileImage;
    private String nickname;
    private List<String> keyword;

    @Builder
    public AccountResponse(String userId, String profileImage, String nickname, List<String> keyword) {
        this.userId = userId;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.keyword = keyword;
    }

    public static AccountResponse of(Account account) {
        return AccountResponse.builder()
                .userId(account.getUserId())
                .profileImage(account.getProfileImage().toString())
                .nickname(account.getNickname())
                .keyword(account.getKeywords())
                .build();
    }
}
