package com.mallang.healingmate.account.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
