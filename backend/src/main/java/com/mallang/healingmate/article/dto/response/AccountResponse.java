package com.mallang.healingmate.article.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallang.healingmate.account.domain.Account;
import lombok.Builder;

/**
 * com.mallang.healingmate.article.dto.response
 * AccountResponse.java
 *
 * @author 서범석
 * @date 2021-05-06 오후 4:38
 * @변경이력
 **/

public class AccountResponse {
    @JsonProperty
    private String userId;
    @JsonProperty
    private String profileImagePath;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String[] keywords;

    @Builder
    public AccountResponse(String userId, String profileImagePath, String nickname, String[] keywords) {
        this.userId = userId;
        this.profileImagePath = profileImagePath;
        this.nickname = nickname;
        this.keywords = keywords;
    }

    public static AccountResponse of(Account account) {
        return AccountResponse.builder()
                .userId(account.getUserId())
//                TODO: 유저의 프로필기능 추가 후 입력
//                .profileImagePath(account.getImage().getPath())
                .nickname(account.getNickname())
                // TODO: 키워드는 keyword쪽에 string[]으로 만드는 메서드 구현해주기
//                .keywords(account.getAccountKeywords().to())
                .build();
    }
}
