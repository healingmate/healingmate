package com.mallang.healingmate.article.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.mallang.healingmate.article.dto
 * ArticleRequest.java
 * @date    2021-04-29 오후 2:53
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
public class ArticleRequest {
    private MultipartFile[] images;
    private String content;
}
