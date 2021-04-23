package com.mallang.healingmate.keyword.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * com.mallang.healingmate.keyword.domain
 * Keyword.java
 * @date    2021-04-21 오후 4:05
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @Builder
    public Keyword(String keyword) {
        this.keyword = keyword;
    }
}
