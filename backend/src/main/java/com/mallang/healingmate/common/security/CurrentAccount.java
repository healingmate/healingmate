package com.mallang.healingmate.common.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.mallang.healingmate.common.security
 * CurrentAccount.java
 * @date    2021-05-01 오후 8:40
 * @author  이아영
 *
 * @변경이력
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account" )
public @interface CurrentAccount {
}
