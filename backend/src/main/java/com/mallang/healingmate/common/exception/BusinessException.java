package com.mallang.healingmate.common.exception;

import lombok.Getter;

/**
 * com.mallang.healingmate.common.exception
 * BusinessException.java
 * @date    2021-04-26 오후 2:45
 * @author  이아영
 *
 * @변경이력
 **/

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;


    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
