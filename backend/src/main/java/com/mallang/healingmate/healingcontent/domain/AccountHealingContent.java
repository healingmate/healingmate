package com.mallang.healingmate.healingcontent.domain;

import com.mallang.healingmate.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.healingcontents.domain
 * AccountHealingContent.java
 * @date    2021-04-22 오후 2:25
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class AccountHealingContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "healing_content_id")
    private HealingContent healingContent;

    @Builder
    public AccountHealingContent(Account account, HealingContent healingContent) {
        this.account = account;
        this.healingContent = healingContent;
    }

    public static AccountHealingContent associate(Account account, HealingContent healingContent) {
        return AccountHealingContent.builder()
                .account(account)
                .healingContent(healingContent)
                .build();
    }
}
