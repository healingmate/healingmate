package com.mallang.healingmate.image.repository;

import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.image.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * com.mallang.healingmate.image.repository
 * ArticleImageRepository.java
 * @date    2021-05-06 오전 5:49
 * @author  서범석
 *
 * @변경이력
 **/

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
    List<ArticleImage> findByArticle(Article article);
}
