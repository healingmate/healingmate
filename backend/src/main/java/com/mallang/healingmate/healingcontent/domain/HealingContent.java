package com.mallang.healingmate.healingcontent.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.healingcontents.domain
 * HealingContent.java
 * @date    2021-04-22 오후 2:25
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HealingContent {
    @Id
    private Long id;

    @Builder
    public HealingContent(Long id) {
        this.id = id;
    }
}
