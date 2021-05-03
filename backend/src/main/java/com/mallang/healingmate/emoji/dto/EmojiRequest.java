package com.mallang.healingmate.emoji.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallang.healingmate.emoji.domain.Emoji;
import lombok.*;

/**
 * com.mallang.healingmate.emoji.dto
 * EmojiRequest.java
 * @date    2021-04-29 오후 2:25
 * @author  서범석
 *
 * @변경이력
 **/

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EmojiRequest {
    private Emoji emojiType;
}
