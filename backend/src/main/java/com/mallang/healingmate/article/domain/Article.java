package com.mallang.healingmate.article.domain;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.emoji.domain.AccountArticleEmojis;
import com.mallang.healingmate.image.domain.ArticleImage;
import com.mallang.healingmate.image.domain.ArticleImages;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * com.mallang.healingmate.article.domain
 * Article.java
 * @date    2021-04-22 오후 2:24
 * @author  서범석, 이아영
 *
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
    private AccountArticleEmojis accountArticleEmojis;

    // TODO: Article Builder
    @Builder
    public Article(Account account, String content) {
        this.account = account;
        this.content = content;
    }

    public void update(String content){
        this.content = content;
    }

    public void addArticleImages(ArticleImage articleImage){
        this.articleImages.add(articleImage);
    }

    public List<String> getImages() {
        return articleImages.toImagePaths();
    }
}
