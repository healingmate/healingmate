package com.mallang.healingmate.emoji.domain;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.ArticleRequest;
import org.springframework.stereotype.Component;

/**
 * com.mallang.healingmate.emoji.domain
 * AccountArticleEmojiMapper.java
 * @date    2021-04-29 오후 10:05
 * @author  서범석
 *
 * @변경이력
 **/

@Component
public class AccountArticleEmojiMapper {
    public AccountArticleEmoji from(Emoji emoji, Article article, Account account) {
        return AccountArticleEmoji.builder()
                .account(account)
                .article(article)
                .emoji(emoji)
                .build();
    }
}
