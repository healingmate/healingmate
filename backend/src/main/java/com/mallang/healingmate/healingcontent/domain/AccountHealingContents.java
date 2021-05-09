package com.mallang.healingmate.healingcontent.domain;

import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
 *
 * @author 서범석, 이아영
 * @date 2021-04-22 오후 2:24
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountHealingContents {
    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountHealingContent> accountHealingContents = new ArrayList<>();

    public static AccountHealingContents empty() {
        return new AccountHealingContents();
    }

    public void add(AccountHealingContent accountHealingContent) {
        if (isContainAccountHealingcontent(accountHealingContent)) {
            throw new EntityException(ErrorCode.DUPLICATED_ENTITY);
        }
        accountHealingContents.add(accountHealingContent);
    }

    public boolean isContainAccountHealingcontent(AccountHealingContent other) {
        return accountHealingContents.stream()
                .anyMatch(
                        accountHealingContent
                                -> accountHealingContent.getAccount().equals(other.getAccount())&&accountHealingContent.getHealingContent().equals(other.getHealingContent())
                );
    }

    public List<HealingContent> getHealingContents() {
        return accountHealingContents.stream()
                .map(AccountHealingContent::getHealingContent)
                .collect(Collectors.toList());
    }
}
