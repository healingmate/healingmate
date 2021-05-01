package com.mallang.healingmate.account.repository;

import com.mallang.healingmate.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * com.mallang.healingmate.account.repository
 * AccountRepository.java
 * @date    2021-05-01 오후 8:33
 * @author  이아영
 *
 * @변경이력
 **/

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(String userId);
    Boolean existsByUserId(String userId);
    Boolean existsByNickname(String nickname);
}
