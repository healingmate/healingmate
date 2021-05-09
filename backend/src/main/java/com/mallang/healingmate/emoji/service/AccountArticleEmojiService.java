package com.mallang.healingmate.emoji.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.repository.AccountRepository;
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
 *
 * @author 서범석
 * @date 2021-04-29 오후 3:02
 * @변경이력
 **/

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class AccountArticleEmojiService {
    private final ArticleRepository articleRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;
    private final AccountRepository accountRepository;

    public void saveEmoji(EmojiRequest emojiRequest, Long articleId, Account account) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> {
            log.error("Entity not found");
            return new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });

        if (accountArticleEmojiRepository.existsByArticleAndAccount(article, account)) {
            log.error("Entity already exists");
            throw new EntityException((ErrorCode.DUPLICATED_ENTITY));
        }
        article.addAccountArticleEmojis(account, Emoji.valueOf(emojiRequest.getEmoji()));
    }

    public void updateEmoji(EmojiRequest emojiRequest, Long articleId, Account account) {
        Article article = findArticle(articleId);
        AccountArticleEmoji accountArticleEmoji = findAccountArticleEmoji(article, account);

        accountArticleEmoji.update(emojiRequest);
    }

    public void deleteEmoji(Long articleId, Account account) {
        Article article = findArticle(articleId);
        AccountArticleEmoji accountArticleEmoji = findAccountArticleEmoji(article, account);

        accountArticleEmojiRepository.delete(accountArticleEmoji);
    }

    public Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });
    }

    public AccountArticleEmoji findAccountArticleEmoji(Article article, Account account) {
        return accountArticleEmojiRepository.findByArticleAndAccount(article, account).orElseThrow(() -> {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });
    }
}
