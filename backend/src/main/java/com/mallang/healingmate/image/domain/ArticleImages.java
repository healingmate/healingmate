package com.mallang.healingmate.image.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * com.mallang.healingmate.image.domain
 * ArticleImages.java
 * @date    2021-04-22 오후 2:24
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class ArticleImages {
    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    List<ArticleImage> articleImages = new ArrayList<>();
}
