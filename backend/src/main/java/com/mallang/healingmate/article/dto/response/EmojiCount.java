package com.mallang.healingmate.article.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * com.mallang.healingmate.article.dto.response
 * EmojiCount.java
 * @date    2021-05-04 오전 5:14
 * @author  서범석
 *
 * @변경이력
 **/

public class EmojiCount {
    @JsonProperty
    private int smile;
    @JsonProperty
    private int crying;
    @JsonProperty
    private int lovely;
    @JsonProperty
    private int grinning;
    @JsonProperty
    private int amazed;

    @Builder
    public EmojiCount(int smile, int crying, int lovely, int grinning, int amazed) {
        this.smile = smile;
        this.crying = crying;
        this.lovely = lovely;
        this.grinning = grinning;
        this.amazed = amazed;
    }
}
