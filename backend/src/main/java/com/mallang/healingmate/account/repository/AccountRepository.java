package com.mallang.healingmate.account.repository;

import com.mallang.healingmate.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(String userId);
    Boolean existsByUserId(String userId);
    Boolean existsByNickname(String nickname);
}
