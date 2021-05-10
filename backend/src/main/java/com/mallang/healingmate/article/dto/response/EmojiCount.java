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
    @JsonProperty("SMILE")
    private Integer smile;
    @JsonProperty("CRYING")
    private Integer crying;
    @JsonProperty("LOVELY")
    private Integer lovely;
    @JsonProperty("GRINNING")
    private Integer grinning;
    @JsonProperty("AMAZED")
    private Integer amazed;

    @Builder
    public EmojiCount(Integer smile, Integer crying, Integer lovely, Integer grinning, Integer amazed) {
        this.smile = smile;
        this.crying = crying;
        this.lovely = lovely;
        this.grinning = grinning;
        this.amazed = amazed;
    }
}
