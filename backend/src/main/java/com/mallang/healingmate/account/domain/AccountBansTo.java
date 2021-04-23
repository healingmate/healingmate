package com.mallang.healingmate.account.domain;

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
 * 나를 차단한 사람들
 * com.mallang.healingmate.account.domain
 * AccountBansTo.java
 * @date    2021-04-22 오후 6:42
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountBansTo {
    @OneToMany(mappedBy = "banTo", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountBan> accountBansTo = new ArrayList<>();
}
