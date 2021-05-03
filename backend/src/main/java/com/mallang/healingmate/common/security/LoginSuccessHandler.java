package com.mallang.healingmate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mallang.healingmate.account.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * com.mallang.healingmate.common.security
 * LoginSuccessHandler.java
 * @date    2021-05-01 오후 8:40
 * @author  이아영
 *
 * @변경이력
 **/

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), new AuthResponse(tokenProvider.createToken(authentication)));
    }
}
