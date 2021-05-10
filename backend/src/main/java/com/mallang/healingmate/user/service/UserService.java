package com.mallang.healingmate.user.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.AccountBan;
import com.mallang.healingmate.account.dto.response.AccountResponse;
import com.mallang.healingmate.account.repository.AccountBanRepository;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.response.ArticleResponse;
import com.mallang.healingmate.article.dto.response.EmojiCount;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.article.repository.ArticleRepository;
import com.mallang.healingmate.common.exception.AuthException;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.emoji.repository.AccountArticleEmojiRepository;
import com.mallang.healingmate.healingcontent.Repository.AccountHealingContentRepository;
import com.mallang.healingmate.healingcontent.Repository.HealingContentRepository;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import com.mallang.healingmate.user.dto.request.EvaluateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * com.mallang.healingmate.user.service
 * UserService.java
 *
 * @author 서범석, 이아영
 * @date 2021-05-07 오전 6:10
 * @변경이력
 **/

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class UserService {

    private final AccountHealingContentRepository accountHealingContentRepository;
    private final HealingContentRepository healingContentRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;
    private final ArticleRepository articleRepository;
    private final AccountBanRepository accountBanRepository;
    private final AccountRepository accountRepository;

    public void banAccount(String userId, Account account) {
        Account banFrom = findAccount(account.getUserId());
        Account banTo = findAccount(userId);
        accountBanRepository.save(AccountBan.ban(banFrom, banTo));
    }

    public void evaluateAccount(EvaluateRequest evaluateRequest, Account account) {
        for (String recommandId : evaluateRequest.getRecommand()) {
            Account recommandAccount = findAccount(recommandId);
            recommandAccount.evaluate();
        }
        for (String banId : evaluateRequest.getBan()) {
            Account banFrom = findAccount(account.getUserId());
            Account banTo = findAccount(banId);
            accountBanRepository.save(AccountBan.ban(banFrom, banTo));
        }
    }

    public void saveHealingContentBookmark(Long contentId, Account account) {
        account = findAccount(account.getUserId());
        HealingContent healingContent = findHealingContent(contentId);
        account.addAccountHealingContents(healingContent);
    }

    public List<Long> findHealingContentBookmarks(Account account) {
        account = findAccount(account.getUserId());
        return account.getHealingContents();
    }

    public void deleteHealingContentBookmark(Long contentId, Account account) {
        account = findAccount(account.getUserId());
        HealingContent healingContent = findHealingContent(contentId);
        AccountHealingContent accountHealingContent = accountHealingContentRepository.findByHealingContentAndAccount(healingContent, account).orElseThrow(() -> {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });
        accountHealingContentRepository.delete(accountHealingContent);
    }

    private Account findAccount(String userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Account not found");
                    throw new EntityException(ErrorCode.ACCOUNT_NOT_FOUND);
                });
    }

    private HealingContent findHealingContent(Long contentId) {
        return healingContentRepository.findById(contentId)
                .orElseThrow(() -> {
                    log.error("healing content not found");
                    throw new EntityException(ErrorCode.HEALING_CONTENT_NOT_FOUND);
                });

    }

    public EntireArticleResponse findAccountArticle(Long cursorId, Integer size, Account account) {
        Pageable pageable = PageRequest.of(0, size);
        Page<Article> articlePage = null;
        if (cursorId == 0) {
            articlePage = articleRepository.findAllByAccountOrderByIdDesc(account, pageable);
        } else {
            articlePage = articleRepository.findAllByIdLessThanAndAccountOrderByIdDesc(cursorId, account, pageable);
        }
        List<Article> articles = articlePage.getContent();

        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = ArticleResponse.of(article);
            Account articleAccount = article.getAccount();
            articleResponse.setAccountResponse(AccountResponse.of(articleAccount));
            articleResponse.setSelf(articleAccount.getId().equals(account.getId()));
            AccountArticleEmoji accountArticleEmoji = accountArticleEmojiRepository.findByArticleAndAccount(article, account).orElse(null);
            if (accountArticleEmoji != null) {
                articleResponse.setLikedEmoji(accountArticleEmoji.getEmoji());
            }

            Integer amazed = accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.AMAZED);
            Integer crying = accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.CRYING);
            Integer grinning = accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.GRINNING);
            Integer lovely = accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.LOVELY);
            Integer smile = accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.SMILE);

            articleResponse.setEmojiCount(EmojiCount.builder()
                    .amazed(amazed)
                    .crying(crying)
                    .grinning(grinning)
                    .lovely(lovely)
                    .smile(smile)
                    .build()
            );
            articleResponses.add(articleResponse);
        }

        return EntireArticleResponse.builder()
                .articleResponses(articleResponses)
                .isLast(articlePage.isLast())
                .build();
    }
}

