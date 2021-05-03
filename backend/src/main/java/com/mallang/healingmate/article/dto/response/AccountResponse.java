package com.mallang.healingmate.article.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallang.healingmate.account.domain.Account;
import lombok.Builder;

public class AccountResponse
{
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

    public static AccountResponse of(Account account){
        return AccountResponse.builder()
                .userId(account.getUserId())
//                TODO: 각 유저의 프로필 사진 저장하기!
//                .profileImagePath(account.getImage().getPath())
                .nickname(account.getNickname())
                // TODO: 키워드는 keyword쪽에 string[]으로 만드는 메서드 구현해주기
//                .keywords(account.getAccountKeywords().to())
                .build();
    }
}
