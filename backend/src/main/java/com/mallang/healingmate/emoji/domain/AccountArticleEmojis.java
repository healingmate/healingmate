package com.mallang.healingmate.emoji.domain;

import com.mallang.healingmate.account.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * com.mallang.healingmate.emoji.domain
 * AccountArticleEmojis.java
 *
 * @author 서범석
 * @date 2021-05-04 오전 5:44
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountArticleEmojis {
    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    List<AccountArticleEmoji> accountArticleEmojis = new ArrayList<>();

    public static AccountArticleEmojis empty() {
        return new AccountArticleEmojis();
    }

    public void add(AccountArticleEmoji accountArticleEmoji) {
        this.accountArticleEmojis.add(accountArticleEmoji);
    }
}
