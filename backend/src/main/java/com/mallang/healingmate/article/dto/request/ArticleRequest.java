package com.mallang.healingmate.article.dto.request;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.domain.Article;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.mallang.healingmate.article.dto
 * ArticleRequest.java
 * @date    2021-04-29 오후 2:53
 * @author  서범석
 *
 * @변경이력
 * 21-05-02 필요없는 lombok 제거
 **/

@Schema(description = "게시물 등록 요청")
@NoArgsConstructor
@Getter
@Setter

public class ArticleRequest {
    @Schema(description = "게시글 이미지")
    private MultipartFile[] images;
    @Schema(description = "게시글 내용", example = "게시글입니다")
    private String content;

    public Article to(ArticleRequest articleRequest, Account account) {
        return Article.builder()
                .account(account)
                .content(articleRequest.getContent())
                .build();
    }
}
