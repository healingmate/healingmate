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
 * 내가 차단한 사람들
 * com.mallang.healingmate.account.domain
 * AccountBansFrom.java
 * @date    2021-04-22 오후 6:42
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class AccountBansFrom {
    @OneToMany(mappedBy = "banFrom", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountBan> accountBansFrom = new ArrayList<>();
}
