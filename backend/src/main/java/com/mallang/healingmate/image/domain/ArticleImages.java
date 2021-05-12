package com.mallang.healingmate.image.domain;

import com.mallang.healingmate.healingcontent.domain.AccountHealingContent;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.mallang.healingmate.image.domain
 * ArticleImages.java
 *
 * @author 서범석, 이아영
 * @date 2021-04-22 오후 2:24
 * @변경이력
 **/

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable

@Getter
@Setter
public class ArticleImages {
    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    List<ArticleImage> articleImages = new ArrayList<>();

    public List<String> toImagePaths() {
        return articleImages.stream().map(articleImage -> articleImage.getImage().getPath())
                .collect(Collectors.toList());
    }

    public static ArticleImages empty() {
        return new ArticleImages();
    }

    public void add(ArticleImage articleImage) {
        articleImages.add(articleImage);
    }
}
