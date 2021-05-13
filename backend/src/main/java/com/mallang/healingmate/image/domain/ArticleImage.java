package com.mallang.healingmate.image.domain;

import com.mallang.healingmate.article.domain.Article;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.image.domain
 * ArticleImage.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-22 오후 2:24
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public ArticleImage(Article article, Image image) {
        this.article = article;
        this.image = image;
    }

    public static ArticleImage associate(Article article, Image image) {
        return ArticleImage.builder().article(article).image(image).build();
    }
}
