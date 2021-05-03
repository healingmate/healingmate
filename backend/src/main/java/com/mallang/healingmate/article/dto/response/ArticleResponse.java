package com.mallang.healingmate.article.dto.response;

import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.image.domain.ArticleImages;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * com.mallang.healingmate.article.dto
 * ArticleResponse.java
 * @date    2021-04-29 오후 2:46
 * @author  서범석
 *
 * @변경이력
 * 21-05-03 emoji관련 컬럼 추가
 **/
@Getter
@Setter

public class ArticleResponse {
    private AccountResponse accountResponse;
    private String content;
    private boolean isSelf;
    private Emoji likedEmoji;
    private EmojiCount emojiCount;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private ArticleImages articleImages;

    @Builder
    public ArticleResponse(AccountResponse accountResponse, String content, boolean isSelf, Emoji likedEmoji, EmojiCount emojiCount, LocalDateTime createdDate, LocalDateTime updateDate, ArticleImages articleImages) {
        this.accountResponse = accountResponse;
        this.content = content;
        this.isSelf = isSelf;
        this.likedEmoji = likedEmoji;
        this.emojiCount = emojiCount;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.articleImages = articleImages;
    }

    // TODO: accountResponse는 기존 account와 다른 정보를 담기에 article에서 바로 가져오지 않습니다
    public static ArticleResponse of(Article article){
        return ArticleResponse.builder()
                .content(article.getContent())
                .createdDate(article.getCreatedDate())
                .updateDate(article.getUpdatedDate())
                .articleImages(article.getArticleImages())
                .build();
    }

    public static List<ArticleResponse> listOf(List<Article> articles){
        List<ArticleResponse> articleResponses = new ArrayList<>();

        for(Article article : articles){
            articleResponses.add(of(article));
        }

        return articleResponses;
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
