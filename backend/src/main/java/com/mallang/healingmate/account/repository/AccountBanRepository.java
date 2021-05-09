package com.mallang.healingmate.account.repository;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.AccountBan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * com.mallang.healingmate.article.repository
 * AccountBanRepository.java
 * @date    2021-05-04 오전 5:14
 * @author  서범석
 *
 * @변경이력
 * 21-05-05 BanFrom을 accountId로 찾아보려고 시도하다가 account로 수정
 **/

public interface AccountBanRepository extends JpaRepository<AccountBan, Long> {
    List<AccountBan> findByBanFrom(Account account);
}
