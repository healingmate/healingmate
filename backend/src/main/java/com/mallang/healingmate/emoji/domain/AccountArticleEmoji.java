package com.mallang.healingmate.emoji.domain;


import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.emoji.dto.EmojiRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.emoji.domain
 * AccountArticleEmoji.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-22 오후 5:55
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class AccountArticleEmoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Enumerated(EnumType.STRING)
    private Emoji emoji;

    @Builder
    public AccountArticleEmoji(Long id, Account account, Article article, Emoji emoji) {
        this.id = id;
        this.account = account;
        this.article = article;
        this.emoji = emoji;
    }

    public void update(EmojiRequest emojiRequest) {
        this.emoji = Emoji.valueOf(emojiRequest.getEmoji());
    }

    public static AccountArticleEmoji associate(Account account, Article article, Emoji emoji) {
        return AccountArticleEmoji.builder().account(account).article(article).emoji(emoji).build();
    }
}
