package com.mallang.healingmate.article.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * com.mallang.healingmate.article.dto
 * EntireArticleResponse.java
 * @date    2021-04-29 오후 2:52
 * @author  서범석
 *
 * @변경이력
 **/
@Getter
@Setter
public class EntireArticleResponse {
    private List<ArticleResponse> ArticleResponses;
    private Boolean isLast;

    @Builder
    public EntireArticleResponse(List<ArticleResponse> articleResponses, boolean isLast) {
        ArticleResponses = articleResponses;
        this.isLast = isLast;
    }
}
