package com.mallang.healingmate.account.service;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.domain.ProfileImage;
import com.mallang.healingmate.account.dto.request.AccountUpdateRequest;
import com.mallang.healingmate.account.dto.request.PasswordUpdateRequest;
import com.mallang.healingmate.account.dto.request.RefreshRequest;
import com.mallang.healingmate.account.dto.request.SignupRequest;
import com.mallang.healingmate.account.dto.response.RefreshResponse;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.common.config.AppProperties;
import com.mallang.healingmate.common.exception.AuthException;
import com.mallang.healingmate.common.exception.EntityException;
import com.mallang.healingmate.common.exception.ErrorCode;
import com.mallang.healingmate.common.exception.InputValueException;
import com.mallang.healingmate.common.security.CustomUserDetailsService;
import com.mallang.healingmate.common.security.TokenProvider;
import com.mallang.healingmate.keyword.domain.Keyword;
import com.mallang.healingmate.keyword.repository.KeywordRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Random;

/**
 * com.mallang.healingmate.account.service
 * AccountService.java
 *
 * @author ?????????
 * @date 2021-05-01 ?????? 8:33
 * @????????????
 **/

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AccountService {

    @Value("${app.token.authorizationHeader}")
    private String authorizationHeader;

    @Value("${app.token.bearerPrefix}")
    private String bearerPrefix;

    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;
    private final KeywordRepository keywordRepository;

    public long createAcount(SignupRequest signupRequest) {
        if (accountRepository.existsByUserId(signupRequest.getUserId())) {
            throw new AuthException(ErrorCode.DUPLICATED_USER_ID);
        }
        if (accountRepository.existsByNickname(signupRequest.getNickname())) {
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


    public RefreshResponse refreshAccessToken(RefreshRequest refreshRequest, HttpServletRequest request) {
        String refreshToken = refreshRequest.getRefreshToken();
        // refresh token??? ????????? ????????? accessToken?????? userId??? ??????
        if (refreshToken != null) {
            String accessToken = getAccessTokenFromRequest(request);
            String userId;
            try {
                userId = tokenProvider.getUserIdFromToken(accessToken);
            } catch (ExpiredJwtException e) {
                userId = e.getClaims().getSubject();
                log.debug("Access Token is expired");
            }
            // refreshToken??? ???????????? ??????(???????????? ?????? ??? ?????? ??????)
            if (tokenProvider.validateRefreshToken(refreshToken, userId)) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                return new RefreshResponse(tokenProvider.createAccessToken(usernamePasswordAuthenticationToken));
            }
        }
        // refresh token??? ????????? exception
        throw new AuthException(ErrorCode.BAD_REFRESH);
    }

    // Request Header?????? Access Token ?????? ????????????
    private String getAccessTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(authorizationHeader);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(bearerPrefix)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void updateAccount(AccountUpdateRequest accountUpdateRequest, Account account) {
        // ????????? ????????? ?????? ??????
        account = findAccount(account.getUserId());
        Account requestAccount = accountUpdateRequest.toAccount();

        if (!account.getNickname().equals(requestAccount.getNickname()) && accountRepository.existsByNickname(requestAccount.getNickname())) {
            throw new AuthException(ErrorCode.DUPLICATED_NICKNAME);
        }

        if (accountUpdateRequest.getKeywords().size() > 3) {
            throw new InputValueException(ErrorCode.INCORRECT_KEYWORD_LENGTH);
        }
        List<Keyword> keywords = keywordRepository.findByKeywordIn(accountUpdateRequest.getKeywords());
        // ?????? ????????? ???????????????, ???????????? ?????? ???????????? ????????? ??????
        if (accountUpdateRequest.getKeywords().size() != keywords.size()) {
            throw new AuthException(ErrorCode.INCORRECT_KEYWORD);
        }

        account.update(requestAccount, keywords);
    }

    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest, Account account) {
        if (passwordEncoder.matches(passwordUpdateRequest.getPassword(), account.getPassword())) {
            account.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
            accountRepository.save(account);
        } else {
            throw new AuthException(ErrorCode.INCORRECT_PASSWORD);
        }
    }

    private Account findAccount(String userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    return new EntityException(ErrorCode.ENTITY_NOT_FOUND);
                });
    }

    public void deleteAccount(Account account) {
        // TODO : account ????????? ?????? ??????????????? ??????
        // accountkeyword, accountban, article, accountArticleEmoji, ????????? ???????????? ????????? ??????, AccountHealingContent, articleImage, image
        accountRepository.delete(account);
    }
}
