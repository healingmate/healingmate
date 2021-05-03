package com.mallang.healingmate.account.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.account.domain
 * Account.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-21 오후 2:19
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class AccountBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ban_from")
    private Account banFrom;

    @ManyToOne
    @JoinColumn(name = "ban_to")
    private Account banTo;
}
