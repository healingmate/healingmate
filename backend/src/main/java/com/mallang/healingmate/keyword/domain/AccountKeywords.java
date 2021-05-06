package com.mallang.healingmate.keyword.domain;

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
import java.util.stream.Collectors;

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
    // getKeywords 할 때 LAZY로 인해 Keyword가 영속상태가 되지않아서 오류남. 따라서 EAGER로 변경
    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AccountKeyword> accountKeywords = new ArrayList<>();

    public void add(AccountKeyword accountKeyword){
        accountKeywords.add(accountKeyword);
    }
    public void clear(){
        accountKeywords.clear();
    }

    public List<String> getKeywords(){
        return accountKeywords.stream()
                .map(AccountKeyword::getKeyword)
                .map(Keyword::getKeyword)
                .collect(Collectors.toList());
    }
}
