package com.mallang.healingmate.emoji.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * com.mallang.healingmate.emoji.domain
 * Emoji.java
 * @date    2021-04-22 오후 2:30
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Emoji {
    @Id
    private Long id;

    @Builder
    public Emoji(Long id) {
        this.id = id;
    }
}
