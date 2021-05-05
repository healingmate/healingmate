package com.mallang.healingmate.healingcontent.Repository;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHealingContentRepository extends JpaRepository<AccountHealingContent,Long> {
    boolean existsByAccountAndHealingContent(Account account, HealingContent healingContent);
}
