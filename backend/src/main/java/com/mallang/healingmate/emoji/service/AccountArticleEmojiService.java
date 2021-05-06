package com.mallang.healingmate.emoji.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.repository.ArticleRepository;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.emoji.dto.EmojiRequest;
import com.mallang.healingmate.emoji.repository.AccountArticleEmojiRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class AccountArticleEmojiService {
    private final ArticleRepository articleRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;


    public void saveEmoji(EmojiRequest emojiRequest, Long articleId, Account account){
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }

        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional = accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(accountArticleEmojiOptional.isPresent()){
            log.error("Entity already exists");
            throw new EntityException(ErrorCode.DUPLICATED_ENTITY);
        }
        accountArticleEmojiRepository.save(new AccountArticleEmoji(Emoji.valueOf(emojiRequest.getEmoji()), article, account));
    }

    public void updateEmoji(EmojiRequest emojiRequest, Long articleId, Account account){
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional =
                accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(!accountArticleEmojiOptional.isPresent()) {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }

        AccountArticleEmoji accountArticleEmoji = accountArticleEmojiOptional.get();

        accountArticleEmoji.update(emojiRequest);
    }

    public void deleteEmoji(Long articleId, Account account){
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(!articleOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        Article article = articleOptional.get();

        Optional<AccountArticleEmoji> accountArticleEmojiOptional =
                accountArticleEmojiRepository.findByArticleAndAccount(article, account);
        if(!accountArticleEmojiOptional.isPresent()) {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }

        AccountArticleEmoji accountArticleEmoji = accountArticleEmojiOptional.get();

        accountArticleEmojiRepository.delete(accountArticleEmoji);
    }


}
