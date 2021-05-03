package com.mallang.healingmate.article.dto;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.image.domain.ArticleImages;
import lombok.Builder;
import java.time.LocalDateTime;

/**
 * com.mallang.healingmate.article.dto
 * ArticleResponse.java
 * @date    2021-04-29 오후 2:46
 * @author  서범석
 *
 * @변경이력
 **/

@Builder
public class ArticleResponse {
    private Account account;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private ArticleImages articleImages;
    private boolean isSelf;
}
