package com.mallang.healingmate.user.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.common.exception.AuthException;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.healingcontent.Repository.AccountHealingContentRepository;
import com.mallang.healingmate.healingcontent.Repository.HealingContentRepository;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * com.mallang.healingmate.user.service
 * UserService.java
 *
 * @author 서범석, 이아영
 * @date 2021-05-07 오전 6:10
 * @변경이력
 **/

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class UserService {

    private final AccountHealingContentRepository accountHealingContentRepository;
    private final HealingContentRepository healingContentRepository;
    private final AccountRepository accountRepository;

    public void saveHealingContentBookmark(Long contentId, Account account) {
        account = findAccount(account.getUserId());
        HealingContent healingContent = findHealingContent(contentId);
        account.addAccountHealingContents(healingContent);
    }

    public List<Long> findHealingContentBookmarks(Account account) {
        account = findAccount(account.getUserId());
        return account.getHealingContents();
    }

    public void deleteHealingContentBookmark(Long contentId, Account account) {
        account = findAccount(account.getUserId());
        HealingContent healingContent = findHealingContent(contentId);
        AccountHealingContent accountHealingContent = accountHealingContentRepository.findByHealingContentAndAccount(healingContent, account).orElseThrow(()->{
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        });
        accountHealingContentRepository.delete(accountHealingContent);
    }

    private Account findAccount(String userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    log.error("Account not found");
                    throw new AuthException(ErrorCode.AUTHENTICATION_ERROR);
                });
    }

    private HealingContent findHealingContent(Long contentId){
        return healingContentRepository.findById(contentId)
                .orElseThrow(()->{
                    log.error("healing content not found");
                    throw new EntityException(ErrorCode.HEALING_CONTENT_NOT_FOUND);
                });

    }
}

