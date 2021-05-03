package com.mallang.healingmate.emoji.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * com.mallang.healingmate.emoji.dto
 * EmojiRequest.java
 * @date    2021-04-29 오후 2:25
 * @author  서범석
 *
 * @변경이력
 * 21-05-02 필요없는 lombok annotation 제거
 **/
@Schema(description = "emoji 등록 요청")
@Getter
public class EmojiRequest {
    @Schema(description = "등록할 emoji 정보", example = "AMAZED")
    private String emoji;
}
