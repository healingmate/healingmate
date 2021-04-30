package com.mallang.healingmate.emoji.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.service.ArticleRepository;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.AccountArticleEmojiMapper;
import com.mallang.healingmate.emoji.domain.Emoji;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * com.mallang.healingmate.emoji.service
 * AccountArticleEmojiService.java
 * @date    2021-04-29 오후 3:02
 * @author  서범석
 *
 * @변경이력
 **/

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class AccountArticleEmojiService {
    private final AccountRepository accountRepository;
    private final ArticleRepository articleRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;
    private final AccountArticleEmojiMapper accountArticleEmojiMapper;
    @Transactional
    public void createEmoji(Emoji emoji, Long articleId, Account account){
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            // TODO : Exception 적용하기
            System.out.println("유효하지 않은 게시글 입니다");
            return;
        }
        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional = accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(accountArticleEmojiOptional.isPresent()){
            // TODO : Exception 적용하기
            System.out.println("이미 emoji를 남긴 게시글 입니다");
            return;
        }

        AccountArticleEmoji accountArticleEmoji = accountArticleEmojiMapper.from(emoji, article, account);
        accountArticleEmojiRepository.save(accountArticleEmoji);
    }

    @Transactional
    public void updateEmoji(Emoji emoji, Long articleId, Account account){

        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            // TODO : Exception 적용하기
            System.out.println("유효하지 않은 게시글 입니다");
            return;
        }
        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional =
                accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(!accountArticleEmojiOptional.isPresent()) {
            // TODO : Exception 적용하기
            System.out.println("등록된 emoji를 찾을 수 없습니다");
            return;
        }

        AccountArticleEmoji accountArticleEmoji = accountArticleEmojiOptional.get();

        accountArticleEmoji.update(emoji);
    }

    @Transactional
    public void deleteEmoji(Long articleId, Account account){

        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            // TODO : Exception 적용하기
            System.out.println("유효하지 않은 게시글 입니다");
            return;
        }
        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional =
                accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(!accountArticleEmojiOptional.isPresent()) {
            // TODO : Exception 적용하기
            System.out.println("등록된 emoji를 찾을 수 없습니다");
            return;
        }

        AccountArticleEmoji accountArticleEmoji = accountArticleEmojiOptional.get();

        accountArticleEmojiRepository.delete(accountArticleEmoji);
    }
}
