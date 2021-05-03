package com.mallang.healingmate.article.api;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.repository.AccountRepository;
import com.mallang.healingmate.article.dto.ArticleRequest;
import com.mallang.healingmate.article.service.ArticleService;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.emoji.dto.EmojiRequest;
import com.mallang.healingmate.emoji.service.AccountArticleEmojiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * com.mallang.healingmate.article.api
 * ArticleController.java
 *
 * @author 서범석
 * @date 2021-04-29 오후 2:25
 * @변경이력
 **/

@RequiredArgsConstructor
@Tag(name = "article", description = "게시글 관련 컨트롤러")
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final AccountArticleEmojiService accountArticleEmojiService;

    @PostMapping
    @Operation(summary = "게시글 등록", description = "content와 images를 입력받아 게시글을 등록합니다.", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<String> createArticle(@ModelAttribute ArticleRequest articleRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.createArticle(articleRequest, account);
        return ResponseEntity.ok("등록에 성공하였습니다");
    }

//    @GetMapping("/articles/{articleId}")
//    @Operation(summary = "게시글 전체 조회", description = "전체 게시글을 조회합니다", security = @SecurityRequirement(name = "Authorization"))
//    private ResponseEntity<List<ArticleResponse>> findArticle(@Parameter(description = "수정사항이 반영된 article의 content")
//                                                 String content, HttpRequest httpRequest)
//    {
//        // TODO: 게시글 불러올때 밴리스트 찾아서 그 사용자 아닌 사람들 게시물만 볼 수 있도록 하기
//        return ResponseEntity.ok(articleService.findAllArticles());
//    }

    @PutMapping("/{articleId}")
    @Operation(summary = "게시글 수정", description = "특정 게시글을 수정합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<String> updateArticle(@PathVariable String articleId, @RequestParam("content") String content, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.updateArticle(content, Long.parseLong(articleId), account);
        return ResponseEntity.ok("잘 수정했어여");
    }

    @DeleteMapping("/{articleId}")
    @Operation(summary = "게시글 삭제", description = "특정 게시글을 삭제합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> deleteArticle(@PathVariable String articleId, @Parameter(hidden = true) @CurrentAccount Account account) {
        articleService.deleteArticle(Long.parseLong(articleId), account);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 등록", description = "특정 게시글에 emoji를 등록합니다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<String> createEmoji(@PathVariable String articleId, @RequestBody EmojiRequest emojiRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        // TODO: Enum으로 정의되어 있지 않은 String에 대한 예외처리
        accountArticleEmojiService.createEmoji(emojiRequest.getEmojiType(), Long.parseLong(articleId), account);
        return ResponseEntity.ok("등록 되었어여");
    }

    @PutMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 변경 ", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<String> updateEmoji(@PathVariable String articleId, @RequestBody EmojiRequest emojiRequest, @Parameter(hidden = true) @CurrentAccount Account account) {
        // TODO: Enum으로 정의되어 있지 않은 String에 대한 예외처리
        accountArticleEmojiService.updateEmoji(emojiRequest.getEmojiType(), Long.parseLong(articleId), account);
        return ResponseEntity.ok("등록 되었어여");
    }

    @DeleteMapping("/{articleId}/emoji")
    @Operation(summary = "특정 게시글 emoji 취소 ", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<String> deleteEmoji(@PathVariable String articleId, @Parameter(hidden = true) @CurrentAccount Account account) {

        // TODO: Enum으로 정의되어 있지 않은 String에 대한 예외처리
        accountArticleEmojiService.deleteEmoji(Long.parseLong(articleId), account);
        return ResponseEntity.ok("등록 되었어여");
    }
}
