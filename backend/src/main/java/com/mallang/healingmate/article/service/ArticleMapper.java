package com.mallang.healingmate.article.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.ArticleRequest;
import org.springframework.stereotype.Component;

/**
 * com.mallang.healingmate.article.service
 * ArticleMapper.java
 * @date    2021-04-29 오후 2:53
 * @author  서범석
 *
 * @변경이력
 **/

@Component
public class ArticleMapper {
    public Article from(ArticleRequest articleRequest, Account account) {
        return Article.builder()
                .account(account)
                .content(articleRequest.getContent())
                .build();
    }
}
