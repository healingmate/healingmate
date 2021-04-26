package com.mallang.healingmate.keyword.domain;

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
 * com.mallang.healingmate.keyword.domain
 * AccountKeywords.java
 * @date    2021-04-21 오후 4:05
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountKeywords {
    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountKeyword> accountKeywords = new ArrayList<>();
}
