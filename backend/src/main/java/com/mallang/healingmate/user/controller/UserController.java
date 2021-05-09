package com.mallang.healingmate.user.controller;

import com.mallang.healingmate.account.domain.Account;
import com.mallang.healingmate.common.security.CurrentAccount;
import com.mallang.healingmate.healingcontent.domain.HealingContent;
import com.mallang.healingmate.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.mallang.healingmate.user.controller
 * UserController.java
 * @date    2021-05-07 오전 6:08
 * @author  서범석, 이아영
 *
 * @변경이력
 **/

@Tag(name = "user", description = "회원 정보 관련 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/healing-content/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹", description = "힐링 콘텐츠를 북마킹한다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> saveHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account){
        userService.saveHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/healing-contents")
    @Operation(summary = "사용자가 북마크한 힐링 콘텐츠 조회 ", description = "사용자가 북마크한 힐링 콘텐츠만 가져온다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<List<HealingContent>> findHealingContentBookmarks(@Parameter(hidden = true) @CurrentAccount Account account){
        List<HealingContent> healingContents = userService.findHealingContentBookmarks(account);
        return ResponseEntity.ok(healingContents);
    }

    @DeleteMapping("/healing-content/{contentId}")
    @Operation(summary = "힐링 콘텐츠 북마킹 제거 ", description = "사용자가 북마크한 힐링 콘텐츠를 제거한다", security = @SecurityRequirement(name = "Authorization"))
    private ResponseEntity<Void> deleteHealingContentBookmark(@PathVariable Long contentId, @Parameter(hidden = true) @CurrentAccount Account account){
        userService.deleteHealingContentBookmark(contentId, account);
        return ResponseEntity.noContent().build();
    }
}
