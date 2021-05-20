package com.mallang.healingmate.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * com.mallang.healingmate.user.dto.request
 * EvaluateRequest.java
 * @date    2021-05-10 오전 10:39
 * @author  이아영
 *
 * @변경이력
 **/

@Schema(description = "회원 평가 요청")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EvaluateRequest {
    @Schema(description = "추천하는 회원 아이디 리스트", example = "[\"abcd1234\", \"penguin\"]")
    List<String> recommand;
    @Schema(description = "차단하는 회원 아이디 리스트", example = "[\"abcd5678\", \"penguin2\"]")
    List<String> ban;
}
