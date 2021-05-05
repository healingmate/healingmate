package com.mallang.healingmate.healingcontent.Repository;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * com.mallang.healingmate.healingcontent.Repository
 * AccountHealingContentRepository.java
 * @date    2021-05-05 오후 9:08
 * @author  서범석
 *
 * @변경이력
 **/

import java.util.List;
import java.util.Optional;

public interface AccountHealingContentRepository extends JpaRepository<AccountHealingContent,Long> {
    boolean existsByAccountAndHealingContent(Account account, HealingContent healingContent);
    List<AccountHealingContent> findAllByAccount(Account account);

    Optional<AccountHealingContent> findByHealingContentAndAccount(HealingContent healingContent, Account account);
}
