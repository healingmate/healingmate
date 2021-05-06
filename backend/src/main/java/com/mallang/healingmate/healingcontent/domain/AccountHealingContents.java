package com.mallang.healingmate.healingcontent.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.mallang.healingmate.healingcontents.domain
 * AccountHealingContents.java
 * @date    2021-04-22 오후 2:24
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountHealingContents {
    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountHealingContent> accountHealingContents = new ArrayList<>();

    public static List<HealingContent> toHealingContents(List<AccountHealingContent> accountHealingContents) {
        return accountHealingContents.stream().map(accountHealingContent -> accountHealingContent.getHealingContent())
                .collect(Collectors.toList());
    }
}
