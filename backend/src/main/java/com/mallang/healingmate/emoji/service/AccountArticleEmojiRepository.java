package com.mallang.healingmate.emoji.service;


import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import com.mallang.healingmate.emoji.domain.AccountArticleEmoji;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountArticleEmojiRepository extends JpaRepository<AccountArticleEmoji, Long> {
    Optional<AccountArticleEmoji> findByArticleAndAccount(Article article, Account account);
}
