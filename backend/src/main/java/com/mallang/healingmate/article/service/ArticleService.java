package com.mallang.healingmate.article.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.AccountBan;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.article.dto.request.ArticleRequest;
import com.mallang.healingmate.article.dto.response.AccountResponse;
import com.mallang.healingmate.article.dto.response.ArticleResponse;
import com.mallang.healingmate.article.dto.response.EmojiCount;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.article.repository.AccountBanRepository;
import com.mallang.healingmate.article.repository.ArticleRepository;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.Emoji;
import com.mallang.healingmate.emoji.repository.AccountArticleEmojiRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * com.mallang.healingmate.article.domain
 * ArticleService.java
 *
 * @author 서범석
 * @date 2021-04-29 오전 2:51
 * @변경이력
 **/
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AccountBanRepository accountBanRepository;
    private final AccountArticleEmojiRepository accountArticleEmojiRepository;
//    private final ArticleMapper articleMapper;


    public void saveArticle(ArticleRequest articleRequest, Account account) {
        Article article = articleRequest.to(articleRequest, account);
        articleRepository.save(article);
    }

    public void updateArticle(String content, Long articleId, Account account) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (!articleOptional.isPresent()) {
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        Article article = articleOptional.get();

        if (!article.getAccount().getId().equals(account.getId())) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        article.update(content);
    }

    public void deleteArticle(Long articleId, Account account) {
        Article article = articleRepository.findById(articleId).get();

        if (article.getAccount().getId() != account.getId()) {
            log.error("No permission");
            throw new EntityException(ErrorCode.UNAUTHORIZED_ACCOUNT);
        }
        articleRepository.delete(article);
    }

    public EntireArticleResponse findAllArticles(int page, int size, Account account) {
        // 페이징을 구현하기 위한 Pageable객체 생성
        Pageable pageable = PageRequest.of(page, size);
        List<AccountBan> accountBans = accountBanRepository.findByBanFrom(account);
        List<Account> accounts = accountBans.stream().map(accountBan -> accountBan.getBanTo())
                .collect(Collectors.toList());
        List<Article> articles = articleRepository.findAllByAccountNotIn(accounts, pageable);

        // TODO: articles를 이용해서
        // User정보, content, isSelf, likedEmoji, EmojiCnt, createdDate, updatedDate, articleImages
        // 를 도출해올 것

        List<ArticleResponse> articleResponses = new ArrayList<>();
        // TODO: 해당 유저가 반응을 남겼는지 파악
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            // 게시글 기본 정보 입력
            ArticleResponse articleResponse = ArticleResponse.of(article);
            // User정보에 필요한 정보 입력
            articleResponse.setAccountResponse(AccountResponse.of(account));
            // 현재 Account정보와 게시글 작성자를 비교해서 본인의 게시물인지 여부를 입력
            articleResponse.setSelf(article.getAccount().getId() == account.getId());
            // 내가 반응을 남긴 글인지 여부 반환
            Optional<AccountArticleEmoji> accountArticleEmojiOptional = accountArticleEmojiRepository.findByArticleAndAccount(article, account);
            if (accountArticleEmojiOptional.isPresent()) {
                articleResponse.setLikedEmoji(accountArticleEmojiOptional.get().getEmoji());
            }
            // 해당 글
            articleResponse.setEmojiCount(
                    EmojiCount.builder()
                            .amazed(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.AMAZED))
                            .crying(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.CRYING))
                            .grinning(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.GRINNING))
                            .lovely(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.LOVELY))
                            .smile(accountArticleEmojiRepository.countByArticleAndEmoji(article, Emoji.SMILE))
                            .build()
            );
            articleResponses.add(articleResponse);
        }
        // TODO: 모든 게시글을 받아와서 리스트에 저장하고, 각각에 대한 정보에 대해서 Response객체로 만들어준다.
        if (page >= articles.size()) {
            log.error("유효하지 않은 page값입니다");
            throw new EntityException(ErrorCode.INVALID_INPUT_VALUE);
        }
        return EntireArticleResponse.builder()
                .articleResponses(articleResponses)
                .isLast(size == articles.size() - 1)
                .build();
    }
}
