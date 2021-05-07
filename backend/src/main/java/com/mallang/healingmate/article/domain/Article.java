package com.mallang.healingmate.article.domain;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.AccountArticleEmojis;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContents;
import com.mallang.healingmate.image.domain.ArticleImage;
import com.mallang.healingmate.image.domain.ArticleImages;
import com.mallang.healingmate.image.domain.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * com.mallang.healingmate.article.domain
 * Article.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-22 오후 2:24
 * @변경이력
 **/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Embedded
    private ArticleImages articleImages = ArticleImages.empty();

    @Embedded
    private AccountArticleEmojis accountArticleEmojis = AccountArticleEmojis.empty();

    // TODO: Article Builder
    @Builder
    public Article(Account account, String content) {
        this.account = account;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void addArticleImages(Image image) {
        this.articleImages.add(ArticleImage.associate(this, image));
    }

    public void addAccountArticleEmojis(Account account, Emoji emoji) {
        this.accountArticleEmojis.add(AccountArticleEmoji.associate(account, this, emoji));
    }
}
