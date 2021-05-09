package com.mallang.healingmate.keyword.domain;


import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.keyword.domain.Keyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.keyword.domain
 * AccountKeyword.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-21 오후 4:05
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class AccountKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    public AccountKeyword(Account account, Keyword keyword) {
        this.account = account;
        this.keyword = keyword;
    }

    public static AccountKeyword associate(Account account, Keyword keyword) {
        return AccountKeyword.builder()
                .account(account)
                .keyword(keyword)
                .build();
    }
}
