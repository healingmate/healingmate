package com.mallang.healingmate.emoji.repository;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import com.mallang.healingmate.emoji.domain.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * com.mallang.healingmate.emoji.service
 * AccountArticleEmojiRepository.java
 * @date    2021-05-04 오전 5:42
 * @author  서범석
 *
 * @변경이력
 **/

public interface AccountArticleEmojiRepository extends JpaRepository<AccountArticleEmoji, Long> {
    Optional<AccountArticleEmoji> findByArticleAndAccount(Article article, Account account);
    int countByArticleAndEmoji(Article article, Emoji emoji);
}
