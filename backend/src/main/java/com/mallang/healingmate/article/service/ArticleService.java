package com.mallang.healingmate.article.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.ArticleRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * com.mallang.healingmate.article.domain
 * ArticleService.java
 * @date    2021-04-29 오전 2:51
 * @author  서범석
 *
 * @변경이력
 **/

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AccountRepository accountRepository;
    private final ArticleMapper articleMapper;

    @Transactional
    public void createArticle(ArticleRequest articleRequest, Account account){
        // description 전달받은 객체를 저장한다 -> 여기서 특별한 처리를 해주고 싶지 않기때문에 일단 accoundId를 넘긴다
        Article article = articleMapper.from(articleRequest, account);
        articleRepository.save(article);
    }

    @Transactional
    public void updateArticle(String content, Long articleId, Account account){
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            System.out.println("유효하지 않은 게시글입니다");
            return;
        }
        Article article = articleOptional.get();

        // TODO : account를 받게 된다면 분기 기능 확인필요
        if (!article.getAccount().equals(account)) {
            // TODO: exception을 반환한다.
            System.out.println("exception");
            return;
        }
        article.update(content);
    }

    public void deleteArticle(Long articleId, Account account) {
        Article article = articleRepository.findById(articleId).get();
        // TODO : account를 받게 된다면 분기 기능 확인필요
        if (!article.getAccount().equals(account)){
            // TODO: exception을 반환한다.
            System.out.println("exception");
            return;
        }
        articleRepository.delete(article);
    }

//    public List<ArticleResponse> findAllArticles() {
//        List<ArticleResponse> ArticleResponses = articleRepository.findAll()
//        return new List<ArticleResponse> ArticleResponses;
//    }

//    public List<ArticleResponse> findAllArticles() {
//        List<Article> articles = articleRepository.findAll();
//        return articleMapper.to(articles);
//    }
}
