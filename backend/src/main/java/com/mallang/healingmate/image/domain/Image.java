package com.mallang.healingmate.image.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.mallang.healingmate.image.domain
 * Image.java
 * @date    2021-04-22 오후 2:24
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String path;

    @Builder
    public Image(String path) {
        this.path = path;
    }
}
