package com.mallang.healingmate.user.controller;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.account.dto.response.AccountResponse;
import com.mallang.healingmate.account.dto.response.RefreshResponse;
import com.mallang.healingmate.article.dto.response.EntireArticleResponse;
import com.mallang.healingmate.common.exception.ErrorResponse;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import com.mallang.healingmate.user.dto.request.EvaluateRequest;
import com.mallang.healingmate.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.mallang.healingmate.user.controller
 * UserController.java
 *
 * @author 서범석, 이아영
 * @date 2021-05-07 오전 6:08
 * @변경이력
 **/

@Tag(name = "user", description = "회원 정보 관련 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/ban/{userId}")
    @Operation(summary = "회원 차단", description = "회원 아이디를 이용해 회원을 차단한다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "회원 차단 성공"),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<Void> banAccount(@PathVariable String userId, @Parameter(hidden = true) @CurrentAccount Account account) {
        userService.banAccount(userId, account);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/evaluate")
    @Operation(summary = "회원 평가", description = "회원 아이디를 이용해 회원을 추천 혹은 차단한다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "회원 평가 성공"),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<Void> evaluateAccount(@RequestBody EvaluateRequest evaluateRequest, @Parameter(hidden = true) @CurrentAccount Account account){
        userService.evaluateAccount(evaluateRequest, account);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/healing-content/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹", description = "힐링 콘텐츠를 북마킹한다", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "힐링 콘텐츠 북마킹 성공"),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "힐링 콘텐츠 중복", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<Void> saveHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account) {
        userService.saveHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/healing-contents")
    @Operation(summary = "회원이 북마크한 힐링 콘텐츠 조회 ", description = "사용자가 북마크한 힐링 콘텐츠만 가져온다", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "200", description = "힐링 콘텐츠 북마크 조회 성공", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<List<Long>> findHealingContentBookmarks(@Parameter(hidden = true) @CurrentAccount Account account) {
        List<Long> healingContents = userService.findHealingContentBookmarks(account);
        return ResponseEntity.ok().body(healingContents);
    }

    @DeleteMapping("/healing-content/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹 제거 ", description = "사용자가 북마크한 힐링 콘텐츠를 제거한다", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "204", description = "힐링 콘텐츠 북마킹 제거 성공"),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<Void> deleteHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account) {
        userService.deleteHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/articles")
    @Operation(summary = "회원이 작성한 게시글 목록 조회", description = "회원 아이디와 cursorId(맨 처음 요청시 0), page size를 이용해 회원이 작성한 게시글 목록을 조회한다.", security = @SecurityRequirement(name = "Authorization"), responses = {
            @ApiResponse(responseCode = "200", description = "회원 게시글 조회 성공", content = @Content(schema = @Schema(implementation = EntireArticleResponse.class))),
            @ApiResponse(responseCode = "401", description = "사용자 인증 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    private ResponseEntity<EntireArticleResponse> findAccountArticle(@RequestParam(required = true) Long cursorId, @RequestParam(required = true) Integer size, @Parameter(hidden = true) @CurrentAccount Account account) {
        EntireArticleResponse entireArticleResponse = userService.findAccountArticle(cursorId, size, account);
        return ResponseEntity.ok().body(entireArticleResponse);
    }
}
