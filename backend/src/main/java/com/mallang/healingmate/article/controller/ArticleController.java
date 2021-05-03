package com.mallang.healingmate.article.controller;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.article.dto.request.ArticleRequest;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.article.service.ArticleService;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.emoji.dto.EmojiRequest;
import com.mallang.healingmate.emoji.service.AccountArticleEmojiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * com.mallang.healingmate.article.api
 * ArticleController.java
 *
 * @author 서범석
 * @date 2021-04-29 오후 2:25
 * @변경이력
 **/

@RequiredArgsConstructor
@Slf4j
@Tag(name = "article", description = "게시글 관련 컨트롤러")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final AccountArticleEmojiService accountArticleEmojiService;

    @PostMapping
    @Operation(summary = "게시글 등록", description = "content와 images를 입력받아 게시글을 등록합니다.", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> saveArticle(@ModelAttribute ArticleRequest articleRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.saveArticle(articleRequest, account);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "전체 게시글을 조회합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<EntireArticleResponse> findArticle(
            @Parameter(hidden = true) @CurrentAccount Account account,
            @RequestParam(value="page") Integer page, @RequestParam(value="size") Integer size)
    {
        EntireArticleResponse entireArticleResponse = articleService.findAllArticles(page, size, account);
        return ResponseEntity.ok(entireArticleResponse);
    }

    @PutMapping("/{articleId}")
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> updateArticle(@PathVariable Long articleId, @RequestParam("content") String content, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.updateArticle(content, articleId, account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{articleId}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> deleteArticle(@PathVariable Long articleId, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.deleteArticle(articleId, account);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 등록", description = "특정 게시글에 emoji를 등록합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> saveEmoji(@PathVariable Long articleId, @RequestBody @Valid EmojiRequest emojiRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        accountArticleEmojiService.saveEmoji(emojiRequest, articleId, account);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 변경 ", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> updateEmoji(@PathVariable Long articleId, @RequestBody @Valid EmojiRequest emojiRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        accountArticleEmojiService.updateEmoji(emojiRequest, articleId, account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 취소 ", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> deleteEmoji(@PathVariable Long articleId, @Parameter(hidden = true) @CurrentAccount Account account) {
        accountArticleEmojiService.deleteEmoji(articleId, account);
        return ResponseEntity.noContent().build();
    }
}
