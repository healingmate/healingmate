package com.mallang.healingmate.common.exception;

import lombok.Getter;

/**
 * com.mallang.healingmate.common.exception
 * ErrorCode.java
 * @date    2021-04-26 오후 2:46
 * @author  이아영
 *
 * @변경이력
 * 21-05-02 Entity관련 에러코드 추가
 **/

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "S_001", "서버가 올바르게 동작하지 않습니다."),
    METHOD_NOT_ALLOWED(405, "S_002", "지원하지 않는 HTTP method메소드입니다."),
    INVALID_INPUT_VALUE(400, "S_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "S_004", "요청 값의 타입이 잘못되었습니다."),

    AUTH_ERROR(400, "AU_001", "인증 관련 오류가 발생했습니다."),
    DUPLICATED_USERID(409, "AU_002", "이미 존재하는 아이디입니다."),
    DUPLICATED_NICKNAME(409, "AU_003", "이미 존재하는 닉네임입니다."),
    BAD_LOGIN(400, "AU_004", "올바르지 않은 아이디 또는 패스워드입니다."),

    ENTITY_NOT_FOUND(404, "EN_001", "Entity를 찾을 수 없습니다"),
    DUPLICATED_ENTITY(409, "EN_002", "이미 존재하는 Entity입니다"),
    UNAUTHORIZED_ACCOUNT(403, "EN_003", "해당 Entity를 수정 또는 삭제할 권한이 없습니다");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
