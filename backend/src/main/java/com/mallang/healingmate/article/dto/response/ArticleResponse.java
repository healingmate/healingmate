package com.mallang.healingmate.article.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallang.healingmate.account.dto.response.AccountResponse;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.emoji.domain.Emoji;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * com.mallang.healingmate.article.dto
 * ArticleResponse.java
 * @date    2021-04-29 오후 2:46
 * @author  서범석
 *
 * @변경이력
 * 21-05-03
 * emoji관련 컬럼 추가
 *
 * 21-05-06
 * articleImages의 반환값을 RestAPI에 맞추어 String[]으로 바꿈
 *
 **/
@Getter

public class ArticleResponse {
    // accountResponse -> user
    @JsonProperty("user")
    private AccountResponse accountResponse;
    private Long articleId;
    private String content;
    private Boolean isSelf;
    private Emoji likedEmoji;
    private EmojiCount emojiCount;
    private String createdDate;
    private String updateDate;
    private List<String> articleImages;

    @Builder
    public ArticleResponse(AccountResponse accountResponse, Long articleId, String content, boolean isSelf, Emoji likedEmoji, EmojiCount emojiCount, LocalDateTime createdDate, LocalDateTime updateDate, List<String> articleImages) {
        this.accountResponse = accountResponse;
        this.articleId = articleId;
        this.content = content;
        this.isSelf = isSelf;
        this.likedEmoji = likedEmoji;
        this.emojiCount = emojiCount;
        this.createdDate = createdDate.toString();
        this.updateDate = updateDate.toString();
        this.articleImages = articleImages;
    }

    public static ArticleResponse of(Article article){
        return ArticleResponse.builder()
                .articleId(article.getId())
                .content(article.getContent())
                .createdDate(article.getCreatedDate())
                .updateDate(article.getUpdatedDate())
                .articleImages(article.getImages())
                .build();
    }

    public void setAccountResponse(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    public void setLikedEmoji(Emoji likedEmoji) {
        this.likedEmoji = likedEmoji;
    }

    public void setEmojiCount(EmojiCount emojiCount) {
        this.emojiCount = emojiCount;
    }
}
