package com.mallang.healingmate.healingcontent.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.healingcontent.Repository.AccountHealingContentRepository;
import com.mallang.healingmate.healingcontent.Repository.HealingContentRepository;
import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
@Transactional
public class HealingContentService {

    private final AccountHealingContentRepository accountHealingContentRepository;
    private final HealingContentRepository healingContentRepository;

    public ResponseEntity<Void> saveHealingContent(Long contentId, Account account) {
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
        return ResponseEntity.noContent().build();
    }
}
