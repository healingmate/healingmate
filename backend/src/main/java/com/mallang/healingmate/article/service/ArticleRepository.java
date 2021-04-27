package com.mallang.healingmate.article.service;

import com.mallang.healingmate.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
