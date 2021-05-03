package com.mallang.healingmate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallang.healingmate.account.dto.request.LoginRequest;
import com.mallang.healingmate.common.exception.AuthException;
import com.mallang.healingmate.common.exception.ErrorCode;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * com.mallang.healingmate.common.security
 * LoginFilter.java
 * @date    2021-05-01 오후 8:40
 * @author  이아영
 *
 * @변경이력
 **/

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        LoginRequest loginRequest;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new AuthException(ErrorCode.BAD_LOGIN);
        }

        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userId, password);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
