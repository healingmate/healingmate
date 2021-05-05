package com.mallang.healingmate.healingcontent.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.healingcontent.Repository.AccountHealingContentRepository;
import com.mallang.healingmate.healingcontent.Repository.HealingContentRepository;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContents;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * com.mallang.healingmate.healingcontent.service
 * HealingContentService.java
 * @date    2021-05-05 오후 9:09
 * @author  서범석
 *
 * @변경이력
 **/

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class HealingContentService {

    private final AccountHealingContentRepository accountHealingContentRepository;
    private final HealingContentRepository healingContentRepository;

    public void saveHealingContentBookmark(Long contentId, Account account) {
        Optional<HealingContent> healingContentOptional = healingContentRepository.findById(contentId);
        if(!healingContentOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        if(accountHealingContentRepository.existsByAccountAndHealingContent(account, healingContentOptional.get())){
            log.error("Entity already exists");
            throw new EntityException(ErrorCode.DUPLICATED_ENTITY);
        }
        accountHealingContentRepository.save(new AccountHealingContent(account, healingContentOptional.get()));
    }

    public List<HealingContent> findHealingContentBookmarks(Account account) {
        return AccountHealingContents.toHealingContents(accountHealingContentRepository.findAllByAccount(account));
    }

    public void deleteHealingContentBookmark(Long contentId, Account account) {
        Optional<HealingContent> healingContentOptional = healingContentRepository.findById(contentId);
        if(!healingContentOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        Optional<AccountHealingContent> accountHealingContentOptional = accountHealingContentRepository.findByHealingContentAndAccount(healingContentOptional.get(), account);
        if(!accountHealingContentOptional.isPresent()){
            log.error("Entity not found");
            throw new EntityException(ErrorCode.ENTITY_NOT_FOUND);
        }
        accountHealingContentRepository.delete(accountHealingContentOptional.get());
    }
}
