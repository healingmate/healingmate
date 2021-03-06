package com.mallang.healingmate.article.repository;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * com.mallang.healingmate.article.service
 * ArticleRepository.java
 * @date    2021-04-29 오후 2:44
 * @author  서범석
 *
 * @변경이력
 * 21-05-03 페이징 처리를 포함한 조회 기능 구현
 **/

public interface ArticleRepository extends JpaRepository<Article, Long> {
    
    Page<Article> findAllByIdLessThanAndAccountOrderByIdDesc(Long cursorId, Account account,Pageable page);
    Page<Article> findAllByAccountOrderByIdDesc(Account account,Pageable page);
    
    Page<Article> findAllByOrderByIdDesc(Pageable pageable);
    Page<Article> findAllByIdLessThanOrderByIdDesc(Long cursorId, Pageable pageable);
    Page<Article> findAllByAccountNotInOrderByIdDesc(List<Account> accounts, Pageable pageable);
    Page<Article> findAllByIdLessThanAndAccountNotInOrderByIdDesc(Long cursorId, List<Account> banToByBanFrom, Pageable pageable);
    
}