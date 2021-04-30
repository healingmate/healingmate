package com.mallang.healingmate.account.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.dto.request.SignupRequest;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.common.exception.AuthException;
import com.mallang.healingmate.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public long createAcount(SignupRequest signupRequest) {
        if(accountRepository.existsByUserId(signupRequest.getUserId())){
            throw new AuthException(ErrorCode.DUPLICATED_USERID);
        }
        if(accountRepository.existsByNickname(signupRequest.getNickname())){
            throw new AuthException(ErrorCode.DUPLICATED_NICKNAME);
        }
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Account account = signupRequest.toAccount();
        Account result = accountRepository.save(account);

        return result.getId();
    }

    @Transactional(readOnly = true)
    public Boolean checkDuplicateNickname(String nickname) {
        return accountRepository.existsByNickname(nickname);
    }


}
