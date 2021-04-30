package com.mallang.healingmate.common.security;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.UserAccount;
import com.mallang.healingmate.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(
                () -> {
                    return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. userId: " + userId);
                }
        );
        return new UserAccount(account);
    }
}
