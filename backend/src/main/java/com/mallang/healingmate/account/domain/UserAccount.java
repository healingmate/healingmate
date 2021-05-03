package com.mallang.healingmate.account.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * com.mallang.healingmate.account.domain
 * UserAccount.java
 * @date    2021-05-01 오후 8:33
 * @author  이아영
 *
 * @변경이력
 **/

@Getter
public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getUserId(),account.getPassword(), List.of(new SimpleGrantedAuthority(account.getRole().toString())));
        this.account = account;
    }
}
