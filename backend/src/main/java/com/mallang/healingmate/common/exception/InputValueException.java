package com.mallang.healingmate.common.exception;

/**
 * com.mallang.healingmate.common.exception
 * AuthException.java
 *
 * @author 이아영
 * @date 2021-05-01 오후 8:40
 * @변경이력
 **/

public class InputValueException extends BusinessException {
    public InputValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
