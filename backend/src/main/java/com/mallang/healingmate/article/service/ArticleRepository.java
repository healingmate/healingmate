package com.mallang.healingmate.article.service;

import com.mallang.healingmate.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * com.mallang.healingmate.article.service
 * ArticleRepository.java
 * @date    2021-04-29 오후 2:44
 * @author  서범석
 *
 * @변경이력
 **/

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
